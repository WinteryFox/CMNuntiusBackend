package com.cm.nuntius.mo

data class StatusReport(
    val code: Int,
    val status: String,
    val reference: String,
    val to: String
)
