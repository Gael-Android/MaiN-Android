package com.MaiN.main_android.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPIService {
    data class User(val student_id : String)

    @POST("/users/add")
    fun addUser(@Body user:User) : Call<Void>
}