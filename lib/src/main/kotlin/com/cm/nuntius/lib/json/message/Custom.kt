package com.cm.nuntius.lib.json.message

import kotlinx.serialization.Serializable

// TODO: Needs to be expanded with whatever other custom data there is
@Serializable
data class Custom(
    val location: Location? = null
)
