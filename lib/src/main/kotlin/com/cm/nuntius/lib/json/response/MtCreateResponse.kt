package com.cm.nuntius.lib.json.response

import kotlinx.serialization.Serializable

@Serializable
data class MtCreateResponse(
    val details: String,
    val errorCode: Int,
    val messages: List<MtMessageResponse>
)
