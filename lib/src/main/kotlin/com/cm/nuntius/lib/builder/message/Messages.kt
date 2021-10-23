package com.cm.nuntius.lib.builder.message

import com.cm.nuntius.lib.Channel
import com.cm.nuntius.lib.json.message.Body
import com.cm.nuntius.lib.json.message.MtMessage
import com.cm.nuntius.lib.json.message.To

class ToBuilder {
    lateinit var number: String
}

class BodyBuilder {
    var type: String = "auto"
    lateinit var content: String
}

class MessageBuilder {
    var reference: String? = null
    private lateinit var body: Body
    private val to = mutableListOf<To>()
    lateinit var from: String
    private val allowedChannels = mutableListOf<Channel>()

    fun body(init: BodyBuilder.() -> Unit) {
        val b = BodyBuilder().apply(init)
        body = Body(b.type, b.content)
    }
    fun to(init: ToBuilder.() -> Unit) {
        val t = ToBuilder().apply(init)
        to.add(To(t.number))
    }
    fun allowedChannels(vararg channels: Channel) = allowedChannels.addAll(channels)
    fun build() = MtMessage(reference, body, to, from, allowedChannels)
}

class MessagesBuilder {
    private val messages = mutableListOf<MtMessage>()

    fun message(init: MessageBuilder.() -> Unit) = messages.add(MessageBuilder().apply(init).build())
    fun build() = messages
}
