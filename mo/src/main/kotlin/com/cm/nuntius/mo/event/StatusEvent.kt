package com.cm.nuntius.mo.event

import com.cm.nuntius.mo.StatusReport

data class StatusEvent(val status: StatusReport) : Event<StatusReport>("STATUS", status)
