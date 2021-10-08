package com.cm.nuntius.lib.json.message

import kotlinx.serialization.Serializable

@Serializable
data class Body(
    val type: String,
    val content: String
)
