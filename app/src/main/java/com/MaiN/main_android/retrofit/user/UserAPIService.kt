package com.MaiN.main_android.retrofit.user

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPIService {
    @POST("/users/add")
    suspend fun addUser(@Body user: User): Response<Unit>
}