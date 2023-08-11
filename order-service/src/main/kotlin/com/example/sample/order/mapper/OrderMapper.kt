package com.example.sample.order.mapper

import com.example.sample.order.domain.entity.Order
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface OrderMapper {

    @Select("SELECT id, timestamp FROM orders")
    fun listAll(): List<Order>

    @Insert("INSERT")
    fun insert()
}