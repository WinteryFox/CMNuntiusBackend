package com.cm.nuntius.lib.response

import kotlinx.serialization.Serializable

@Serializable
data class MtCreateResponse(
    val details: String,
    val errorCode: Int,
    val messages: List<MtMessageResponse>
)
