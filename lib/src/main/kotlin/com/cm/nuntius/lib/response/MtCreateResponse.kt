package com.cm.nuntius.lib.response

data class MtCreateResponse(
    val details: String,
    val errorCode: Int,
    val messages: List<MtMessageResponse>
)
