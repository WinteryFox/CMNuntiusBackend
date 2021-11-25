package com.cm.nuntius.mt.json.request

data class FetchMediaRequest(
    val name: String,
    val contentType: String,
    val uri: String
)
