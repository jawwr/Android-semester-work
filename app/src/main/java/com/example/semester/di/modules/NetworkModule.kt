package com.example.semester.di.modules

import com.example.semester.data.api.DishService
import com.example.semester.data.api.OrderService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    fun provideDishService(): DishService = getRetrofitInstance()
        .create(DishService::class.java)

    @Provides
    fun provideOrderService(): OrderService = getRetrofitInstance()
        .create(OrderService::class.java)

    private fun getRetrofitInstance(): Retrofit = Retrofit.Builder()
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                ).build()
        )
        .baseUrl("http://10.0.2.2:8080/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}