package com.gezanoletti.demospringbootkafkaconsumer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer
import org.springframework.kafka.listener.ContainerProperties.AckMode.MANUAL_IMMEDIATE

@Configuration
class KafkaConf {

    @Bean
    fun kafkaListenerContainerFactory(consumerFactory: ConsumerFactory<String, String>): KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
        factory.consumerFactory = consumerFactory
        factory.setConcurrency(10)
        factory.containerProperties.pollTimeout = 3000

        /*
        AckMode.MANUAL_IMMEDIATE will commit the offsets to kafka immediately, without waiting for any
        other kind of events to occur.

        But AckMode.MANUAL will work similar to AckMode.BATCH, which means after the acknowledge() method
        is called on a message, the system will wait till all the messages received by the poll() method have
        been acknowledged. This could take a long time, depending on your setup.
         */
        factory.containerProperties.ackMode = MANUAL_IMMEDIATE

        factory.containerProperties.isSyncCommits = true
        return factory
    }
}
