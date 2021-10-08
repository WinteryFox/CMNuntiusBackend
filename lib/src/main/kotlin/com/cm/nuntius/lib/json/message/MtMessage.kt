package com.cm.nuntius.lib.json.message

import kotlinx.serialization.Serializable

@Serializable
data class MtMessage(
    val reference: String,
    val body: Body,
    val to: List<To>,
    val from: String,
    val allowedChannels: List<String>
)
