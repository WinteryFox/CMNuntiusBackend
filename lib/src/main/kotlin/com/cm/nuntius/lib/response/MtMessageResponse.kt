package com.cm.nuntius.lib.response

import kotlinx.serialization.Serializable

@Serializable
data class MtMessageResponse(
    val to: String,
    val status: String,
    val reference: String?,
    val parts: Int,
    val messageDetails: String?,
    val messageErrorCode: Int
)
