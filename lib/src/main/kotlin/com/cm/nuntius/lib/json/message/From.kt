package com.cm.nuntius.lib.json.message

import kotlinx.serialization.Serializable

@Serializable
data class From(
    val number: String,
    val name: String
)
