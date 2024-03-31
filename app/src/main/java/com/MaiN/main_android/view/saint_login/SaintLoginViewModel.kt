package com.MaiN.main_android.view.saint_login

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.MaiN.main_android.retrofit.RetrofitConnection
import com.MaiN.main_android.retrofit.user.User
import com.MaiN.main_android.retrofit.user.UserAPIService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaintLoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _stateFlow: MutableStateFlow<SaintLoginState> = MutableStateFlow(SaintLoginState())
    val stateFlow: StateFlow<SaintLoginState> = _stateFlow.asStateFlow()

    private val retrofitAPI = RetrofitConnection.getInstance().create(UserAPIService::class.java)


    fun addUser(schoolNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = retrofitAPI.addUser(User(schoolNumber))
                if (response.isSuccessful) {
                    Log.d("KWK-addUser-SUCCESS", response.body().toString())
                } else {
                    Log.d("KWK-addUser-FAIL", response.errorBody()?.string()!!)
                }
            } catch (e: Exception) {
                Log.d("KWK-addUser-Exception", e.message.toString())
            }
        }
    }
}