package mathias8dev.github.io.springkafka.controllers

import mathias8dev.github.io.springkafka.configurations.KafkaSender
import mathias8dev.github.io.springkafka.configurations.User
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/messaging")
class MessagingController(
    private val kafkaSender: KafkaSender
) {

    @RequestMapping("/hello")
    fun hello(): String {
        return "Hello World"
    }

    @PostMapping("/send")
    fun send(@RequestPart message: String): String {
        kafkaSender.sendMessage("$message; say hello to Kafka", "topic-1")
        return "Message $message sent to Kafka"
    }

    @PostMapping("/send-user")
    fun sendUser(@RequestPart user: User): String {
        kafkaSender.sendUserMessage(user, "topic-3")
        return "User $user sent to Kafka"
    }
}