package mathias8dev.github.io.springkafka.configurations

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.PartitionOffset
import org.springframework.kafka.annotation.TopicPartition
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component


@Component
class KafkaListenerExample {

    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["topic-1"], groupId = "group1")
    fun listener(
        @Payload data: String,
        @Header(KafkaHeaders.RECEIVED_PARTITION) partition: Int,
        @Header(KafkaHeaders.OFFSET) offset: Int
    ) {
        logger.info("Received message [{}] in group1", data)
    }


    @KafkaListener(
        groupId = "group2",
        topicPartitions = [
            TopicPartition(
                topic = "topic-2",
                partitionOffsets = [
                    PartitionOffset(partition = "0", initialOffset = "0"),
                    PartitionOffset(partition = "3", initialOffset = "0")
                ]
            )
        ]
    )
    fun listenToPartition(
        @Payload message: String?,
        @Header(KafkaHeaders.RECEIVED_PARTITION) partition: Int
    ) {
        logger.info(
            "Received Message [{}] from partition-{}",
            message,
            partition
        )
    }

    @KafkaListener(topics = ["topic-3"], groupId = "user-group", containerFactory = "userKafkaListenerContainerFactory")
    fun listenerWithMessageConverter(@Payload user: User?, @Headers headers: MessageHeaders) {
        logger.info("Received message through MessageConverterUserListener [{}]", user)
    }
}