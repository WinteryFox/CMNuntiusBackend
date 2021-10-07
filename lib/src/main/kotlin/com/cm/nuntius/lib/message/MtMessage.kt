package com.cm.nuntius.lib.message

data class MtMessage(
    val reference: String,
    val body: Body,
    val to: List<To>,
    val from: String,
    val allowedChannels: List<String>
)
