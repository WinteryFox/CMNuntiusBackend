package com.cm.nuntius.mo

import com.cm.nuntius.mo.message.Message
import kotlinx.coroutines.reactor.mono
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MoController {
    @PostMapping("/mo")
    fun receive(@RequestBody message: Message) = mono {
        println(message.content.text)
    }
}
