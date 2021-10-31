package com.cm.nuntius.lib.json.response

import kotlinx.serialization.Serializable

@Serializable
data class StatusResponse(
    val messages: Msg
)
