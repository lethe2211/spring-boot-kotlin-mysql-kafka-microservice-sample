package com.example.sample.order.controller.response

import com.example.sample.order.domain.entity.Order

data class OrderListResponse(
    val orderList: List<Order>
)