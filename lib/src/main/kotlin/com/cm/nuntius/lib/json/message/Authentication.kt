package com.cm.nuntius.lib.json.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Authentication(
    @SerialName("producttoken")
    val token: String
)
