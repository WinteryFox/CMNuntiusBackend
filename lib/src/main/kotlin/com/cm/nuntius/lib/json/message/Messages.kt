package com.cm.nuntius.lib.json.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Messages(
    val authentication: Authentication,
    @SerialName("msg")
    val messages: List<MtMessage>
)
