package com.cm.nuntius.lib.json.message

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val latitude: Float,
    val longitude: Float,
    val label: String,
    val searchQuery: String
)
