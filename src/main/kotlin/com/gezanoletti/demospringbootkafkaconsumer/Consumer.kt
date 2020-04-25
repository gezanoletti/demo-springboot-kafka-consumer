package com.gezanoletti.demospringbootkafkaconsumer

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class Consumer {
    private val logger: Logger = LoggerFactory.getLogger(Consumer::class.java)

    @KafkaListener(topics = [TOPIC_NAME_NORMAL, TOPIC_NAME_COMPACT], groupId = GROUP_ID_CONFIG)
    fun listen(record: ConsumerRecord<String, String>, acknowledgment: Acknowledgment) {
        try {
            logger.info("Record: key ${record.key()}, value ${record.value()}, partition ${record.partition()}, offset ${record.offset()}")
           // acknowledgment.acknowledge()

        } catch (e: Exception) {
            logger.error(e.message, e)
        }
    }

    companion object {
        const val TOPIC_NAME_NORMAL = "demo-basic-kafka-partitions"
        const val TOPIC_NAME_COMPACT = "demo-basic-kafka-partitions-compact"
        const val GROUP_ID_CONFIG = "consumerGroup3"
    }
}
