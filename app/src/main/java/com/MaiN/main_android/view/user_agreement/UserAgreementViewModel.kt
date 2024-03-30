package com.MaiN.main_android.view.user_agreement

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class UserAgreementViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<UserAgreementState> =
        MutableStateFlow(UserAgreementState())

    val stateFlow: StateFlow<UserAgreementState> = _stateFlow.asStateFlow()

}