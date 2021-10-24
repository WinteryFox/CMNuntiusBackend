package com.cm.nuntius.lib.json.request

import com.cm.nuntius.lib.json.message.Messages
import kotlinx.serialization.Serializable

@Serializable
data class MessageCreateRequest(
    val messages: Messages
)
