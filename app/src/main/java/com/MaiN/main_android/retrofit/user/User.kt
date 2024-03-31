package com.MaiN.main_android.retrofit.user

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("student_id")
    val studentId: String
)
