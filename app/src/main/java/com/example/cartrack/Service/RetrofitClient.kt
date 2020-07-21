package com.example.cartrack.Service

import com.example.cartrack.Common.Common.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null

    @JvmStatic
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }
}