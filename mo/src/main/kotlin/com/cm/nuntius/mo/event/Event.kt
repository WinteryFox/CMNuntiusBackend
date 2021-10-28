package com.cm.nuntius.mo.event

sealed class Event<T>(
    val payload: T
)
