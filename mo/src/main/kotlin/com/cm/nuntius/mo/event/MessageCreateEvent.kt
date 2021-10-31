package com.cm.nuntius.mo.event

import com.cm.nuntius.lib.json.message.MoMessage

class MessageCreateEvent(message: MoMessage) : Event<MoMessage>("MESSAGE", message)
