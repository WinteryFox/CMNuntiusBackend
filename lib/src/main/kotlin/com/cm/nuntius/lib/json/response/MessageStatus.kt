package com.cm.nuntius.lib.json.response

import kotlinx.serialization.Serializable

@Serializable
data class Msg(
    val msg: MessageStatus
)

@Serializable
data class MessageStatus(
    val received: String,
    val to: String,
    val reference: String,
    val status: Status,
    val operator: String
)

@Serializable
data class Status(
    val code: String,
    val errorCode: String,
    val errorDescription: String
)
