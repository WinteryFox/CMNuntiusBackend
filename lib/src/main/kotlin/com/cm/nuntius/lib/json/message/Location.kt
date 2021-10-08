package com.cm.nuntius.lib.json.message

data class Location(
    val latitude: Float,
    val longitude: Float,
    val label: String,
    val searchQuery: String
)