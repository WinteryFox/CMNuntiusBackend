package com.cm.nuntius.lib.json.message

import com.cm.nuntius.lib.serializer.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class MoMessage(
    val from: From,
    val to: To,
    val message: Content,
    val reference: String,
    val messageContext: String,
    val groupings: List<String>,
    val time: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    val timeUtc: LocalDateTime,
    val channel: String
)
