package com.cm.nuntius.mo.message

data class Location(
    val latitude: Float,
    val longitude: Float,
    val label: String,
    val searchQuery: String
)
