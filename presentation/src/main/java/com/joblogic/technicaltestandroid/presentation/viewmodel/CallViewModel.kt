package com.joblogic.technicaltestandroid.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.joblogic.technicaltestandroid.domain.Resource
import com.joblogic.technicaltestandroid.domain.interactor.ListUserUseCase
import com.joblogic.technicaltestandroid.domain.models.UserModel
import com.joblogic.technicaltestandroid.presentation.utils.CoroutineContextProvider
import com.joblogic.technicaltestandroid.presentation.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

sealed class CallUIState {
    data class Loading(val isLoading: Boolean = false) : CallUIState()
    data class Error(var error: String = "") : CallUIState()
    data class Success(val data: List<UserModel>) : CallUIState()
}


@HiltViewModel
class CallViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val listUserUseCase: ListUserUseCase,
) : BaseViewModel(contextProvider) {

    private val _callSuccess = MutableStateFlow<CallUIState>(CallUIState.Loading(false))
    val callSuccess = _callSuccess.asStateFlow()

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.d("Error: ${exception.message}")
    }

    init {
        getListUser()
    }
  private  fun getListUser() {
        launchCoroutineIO {
            listUserUseCase.execute(Unit).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        Timber.i("HAOHAO : Resource.Loading")

                        _callSuccess.value = CallUIState.Loading(true)
                    }
                    is Resource.Success -> {
                        Timber.i("HAOHAO : Resource.Success ")

                        result.data?.let { it ->

                            _callSuccess.value = CallUIState.Success(it)
                        }
                    }
                    is Resource.Error -> {
                        Timber.i("HAOHAO : Resource.Error ${result.message}")

                        _callSuccess.value = CallUIState.Error(result.message!!)
                    }
                }

            }.launchIn(viewModelScope)
        }
    }
}
