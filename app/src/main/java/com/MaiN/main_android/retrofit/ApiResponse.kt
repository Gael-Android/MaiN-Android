package com.MaiN.main_android.retrofit

sealed class ApiResponse {
    data object Success : ApiResponse()
    data class Failure(val failMessage: String) : ApiResponse()
    data class Error(val errorMessage: String) : ApiResponse()
}