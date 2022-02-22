package com.ignation.speisefant.domain

data class Type(val title: String)

object TypeSource {
    val types = listOf(
        Type("Milcherzeugnis"),
        Type("Fleisch, Geflügel & Fisch"),
        Type("Bäckerei"),
        Type("Aufschnitt"),
        Type("Nahrungsmittel"),
        Type("Tiefkühl"),
        Type("Süßes & Snacks"),
        Type("Fertiggerichte"),
        Type("Kaffee & Tee"),
        Type("Getränke"),
        Type("Alkohol"),
        Type("Sonstiges"),
        Type("Hygieneartikel"),
        Type("Haushalt")
    )
}
