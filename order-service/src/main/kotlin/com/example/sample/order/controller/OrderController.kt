package com.example.sample.order.controller

import com.example.sample.order.controller.response.OrderListResponse
import com.example.sample.order.domain.entity.Order
import com.example.sample.order.service.OrderService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    private val service: OrderService,
    @Value("\${kafka.topics.name}") val topic: String,
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping(value = ["/order"])
    fun list(): ResponseEntity<OrderListResponse> {
        return ResponseEntity.ok(OrderListResponse((service.list())))
    }

    @PostMapping(value = ["/order"])
    fun createNew(@Validated @RequestBody order: Order): ResponseEntity<Any> {
        return try {
            log.info("Sending message to Kafka {}", order)
            val message: Message<Order> = MessageBuilder
                .withPayload(order)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader("X-Custom-Header", "Custom header here")
                .build()
            kafkaTemplate.send(message)
            log.info("Message sent with success")
            ResponseEntity.ok().build()
        } catch (e: Exception) {
            log.error("Exception: {}", e)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error to send message")
        }
    }
}