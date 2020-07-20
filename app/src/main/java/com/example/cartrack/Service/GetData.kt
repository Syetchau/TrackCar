package com.example.cartrack.Service

import com.example.cartrack.Model.User
import retrofit2.Call
import retrofit2.http.GET

interface GetData {
    @get:GET("/users")
    val allUserList: Call<List<User?>?>?
}