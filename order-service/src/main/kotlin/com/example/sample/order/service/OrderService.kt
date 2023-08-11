package com.example.sample.order.service

import com.example.sample.order.domain.entity.Order

interface OrderService {
    fun list(): List<Order>
}