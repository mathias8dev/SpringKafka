package mathias8dev.github.io.springkafka.configurations

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class KafkaSender(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val userKafkaTemplate: KafkaTemplate<String, User>
) {

    private val logger = LoggerFactory.getLogger(javaClass)


    fun sendMessage(message: String, topicName: String) {
        logger.info("Sending message: $message to topic: $topicName")
        logger.info("------------------------------------------------")
        kafkaTemplate.send(topicName, message)
    }

    fun sendUserMessage(user: User, topicName: String) {
        logger.info("Sending message: $user to topic: $topicName")
        logger.info("------------------------------------------------")
        userKafkaTemplate.send(topicName, user)
    }
}