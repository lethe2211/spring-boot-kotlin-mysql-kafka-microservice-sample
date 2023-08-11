package com.example.sample.order.repository.impl

import com.example.sample.order.domain.entity.Order
import com.example.sample.order.repository.OrderRepository
import com.example.sample.order.mapper.OrderMapper
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryImpl(
    val mapper: OrderMapper
) : OrderRepository {

    override fun listAll(): List<Order> {
        return mapper.listAll()
    }
}