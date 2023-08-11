package com.example.sample.order.repository

import com.example.sample.order.domain.entity.Order

interface OrderRepository {
    fun listAll(): List<Order>
}