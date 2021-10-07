package com.cm.nuntius.lib.message

data class Message(
    val body: Body,
    val to: List<To>,
    val from: String,
    val allowedChannels: List<String>
)
