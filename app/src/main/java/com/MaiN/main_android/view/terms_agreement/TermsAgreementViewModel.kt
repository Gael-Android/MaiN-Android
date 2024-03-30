package com.MaiN.main_android.view.terms_agreement

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TermsAgreementViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<TermsAgreementState> =
        MutableStateFlow(TermsAgreementState())

    val stateFlow: StateFlow<TermsAgreementState> = _stateFlow.asStateFlow()

}