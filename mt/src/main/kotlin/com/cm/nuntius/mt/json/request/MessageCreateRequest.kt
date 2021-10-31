package com.cm.nuntius.mt.json.request

import com.cm.nuntius.lib.Channel

data class MessageCreateRequest(
    val sender: String,
    val recipient: String,
    val channel: Channel,
    val content: String
)
