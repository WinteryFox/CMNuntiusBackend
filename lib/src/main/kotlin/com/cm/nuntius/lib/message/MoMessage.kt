package com.cm.nuntius.lib.message

import java.time.LocalDateTime

data class MoMessage(
    val from: From,
    val to: To,
    val message: Content,
    val reference: String,
    val groupings: List<String>,
    val time: String,
    val timeUtc: LocalDateTime,
    val channel: String
)
