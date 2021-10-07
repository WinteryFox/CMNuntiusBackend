package com.cm.nuntius.lib.message

import com.fasterxml.jackson.annotation.JsonProperty

class ToBuilder {
    lateinit var number: String
}

class BodyBuilder {
    lateinit var type: String
    lateinit var content: String
}

class MessageBuilder {
    private lateinit var body: Body
    private val to = mutableListOf<To>()
    lateinit var from: String
    private val allowedChannels = mutableListOf<String>()

    fun body(init: BodyBuilder.() -> Unit) {
        val b = BodyBuilder().apply(init)
        body = Body()
        body.content = b.content
        body.type = b.type
    }
    fun to(init: ToBuilder.() -> Unit) {
        val t = ToBuilder().apply(init)
        to.add(To(t.number))
    }
    fun allowedChannels(vararg channels: String) = allowedChannels.addAll(channels)
    fun build() = Message(body, to, from, allowedChannels)
}

data class MessageCreateRequest(
    val authentication: Authentication,
    @JsonProperty("msg")
    val messages: List<Message>
)

class MessagesBuilder {
    private val messages = mutableListOf<Message>()

    fun message(init: MessageBuilder.() -> Unit) = messages.add(MessageBuilder().apply(init).build())
    fun build(token: String) = MessageCreateRequest(Authentication(token), messages)
}
