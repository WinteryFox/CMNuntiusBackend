package com.cm.nuntius.mo.event

sealed class Event<T>(
    val type: String,
    val payload: T
)
