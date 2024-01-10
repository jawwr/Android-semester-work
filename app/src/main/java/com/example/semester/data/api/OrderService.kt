package com.example.semester.data.api

import com.example.semester.data.models.Order
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderService {
    @POST("orders")
    suspend fun createOrder(@Body dishIds: Collection<Int>): Response<Order>

    @GET("orders/{id}")
    suspend fun getOrderById(@Path("id") id: Int): Response<Order>
}