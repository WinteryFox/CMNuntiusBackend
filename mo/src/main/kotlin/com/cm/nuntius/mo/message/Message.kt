package com.cm.nuntius.mo.message

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class Message(
    val from: From,
    val to: To,
    @JsonProperty("message")
    val content: Content,
    val reference: String,
    val groupings: List<String>,
    @JsonProperty("timeUtc")
    val time: LocalDateTime,
    val channel: String
)
