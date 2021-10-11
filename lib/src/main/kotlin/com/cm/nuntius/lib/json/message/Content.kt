package com.cm.nuntius.lib.json.message

import kotlinx.serialization.Serializable

@Serializable
data class Content(
    val text: String,
    val media: Media,
    val custom: Custom
)
