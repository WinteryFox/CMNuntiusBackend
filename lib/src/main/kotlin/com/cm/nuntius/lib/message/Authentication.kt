package com.cm.nuntius.lib.message

import com.fasterxml.jackson.annotation.JsonProperty

data class Authentication(
    @JsonProperty("producttoken")
    val token: String
)
