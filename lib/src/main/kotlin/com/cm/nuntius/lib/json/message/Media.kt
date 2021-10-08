package com.cm.nuntius.lib.json.message

import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val mediaUri: String,
    val contentType: String,
    val title: String
)
