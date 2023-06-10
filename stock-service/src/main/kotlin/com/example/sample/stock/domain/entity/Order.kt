package com.example.sample.stock.domain.entity

import com.fasterxml.jackson.annotation.JsonProperty

data class Order(
    @JsonProperty("id")
    val id: Long,
    @JsonProperty("name")
    val name: String
)