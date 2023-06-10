package com.example.sample.order.configuration

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin


@Configuration
class KafkaTopicConfig(
    @Value("\${kafka.bootstrapAddress}")
    private val servers: String,
    @Value("\${kafka.topics.name}")
    private val topic: String
) {

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        val configs: Map<String, Any?> = mapOf(
            AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to servers
        )
        return KafkaAdmin(configs)
    }

    @Bean
    fun porduto(): NewTopic {
        return NewTopic(topic, 1, 1.toShort())
    }
}