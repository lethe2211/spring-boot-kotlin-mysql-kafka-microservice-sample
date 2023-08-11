package com.example.sample.order.service.impl

import com.example.sample.order.domain.entity.Order
import com.example.sample.order.repository.OrderRepository
import com.example.sample.order.service.OrderService
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(
    val repository: OrderRepository
) : OrderService {
    override fun list(): List<Order> {
        return repository.listAll()
    }
}