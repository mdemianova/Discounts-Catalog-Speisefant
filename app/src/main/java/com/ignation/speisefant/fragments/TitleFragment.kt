package com.ignation.speisefant.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.ignation.speisefant.R
import com.ignation.speisefant.adapters.CategoryAdapter
import com.ignation.speisefant.adapters.ShopAdapter
import com.ignation.speisefant.databinding.FragmentTitleBinding
import com.ignation.speisefant.notification.createChannel
import com.ignation.speisefant.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

const val MY_REQUEST_CODE = 100

@AndroidEntryPoint
class TitleFragment : Fragment() {

    private var _binding: FragmentTitleBinding? = null
    private val binding get() = _binding!!
    private val appUpdateManager: AppUpdateManager by lazy { AppUpdateManagerFactory.create(this.requireContext()) }

    private val productViewModel: ProductViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTitleBinding.inflate(inflater, container, false)

        createChannel(
            getString(R.string.notification_channel_id),
            getString(R.string.notification_channel_name),
            requireContext()
        )

        appUpdateManager.registerListener {
            if (it.installStatus() == InstallStatus.DOWNLOADED) {
                showUpdateDownloadedSnackbar()
            }
        }

        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            if (it.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && it.isUpdateTypeAllowed(
                    AppUpdateType.FLEXIBLE
                )
            ) {
                appUpdateManager.startUpdateFlowForResult(
                    it,
                    AppUpdateType.FLEXIBLE,
                    this.requireActivity(),
                    MY_REQUEST_CODE
                )
            }
        }.addOnFailureListener {
            Log.e("TitleFragment", "Failed to check for update: $it")
        }

        productViewModel.eventNetworkError.observe(this.viewLifecycleOwner) {
            if (it) {
                Toast.makeText(activity, getString(R.string.network_error), Toast.LENGTH_SHORT)
                    .show()
                productViewModel.errorShown()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = CategoryAdapter {
            val action =
                TitleFragmentDirections.actionTitleFragmentToProductByTypeFragment(it.title)
            this.findNavController().navigate(action)
        }
        binding.recyclerViewCategory.adapter = categoryAdapter

        val shopAdapter = ShopAdapter {
            val action =
                TitleFragmentDirections.actionTitleFragmentToProductByShopFragment(it.title)
            this.findNavController().navigate(action)
        }
        binding.recyclerViewShop.adapter = shopAdapter

        binding.swiperefresh.setOnRefreshListener {
            productViewModel.refreshDataFromRepository()
            binding.swiperefresh.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            if (it.installStatus() == InstallStatus.DOWNLOADED) {
                showUpdateDownloadedSnackbar()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showUpdateDownloadedSnackbar() {
        Snackbar.make(
            binding.root,
            getString(R.string.update_downloaded),
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(getString(R.string.install)) { appUpdateManager.completeUpdate() }
            .show()
    }
}