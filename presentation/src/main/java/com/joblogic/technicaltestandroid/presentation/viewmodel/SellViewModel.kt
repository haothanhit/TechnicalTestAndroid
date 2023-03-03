package com.joblogic.technicaltestandroid.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.joblogic.technicaltestandroid.domain.Resource
import com.joblogic.technicaltestandroid.domain.interactor.GetListProductCacheUseCase
import com.joblogic.technicaltestandroid.domain.models.ProductModel
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

sealed class SellUIState {
    data class Loading(val isLoading: Boolean = false) : SellUIState()
    data class Error(var error: String = "") : SellUIState()
    data class Success(val data: List<ProductModel>) : SellUIState()
}


@HiltViewModel
class SellViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val getListProductCacheUseCase: GetListProductCacheUseCase,
) : BaseViewModel(contextProvider) {

    private val _sellSuccess = MutableStateFlow<SellUIState>(SellUIState.Loading(false))
    val sellSuccess = _sellSuccess.asStateFlow()

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.d("Error: ${exception.message}")
    }

    init {
        getListSell()
    }

    private fun getListSell() {
        launchCoroutineIO {
            getListProductCacheUseCase.execute(Unit).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        Timber.i("HAOHAO : Resource.Loading")

                        _sellSuccess.value = SellUIState.Loading(true)
                    }
                    is Resource.Success -> {
                        Timber.i("HAOHAO : Resource.Success")

                        result.data?.let { it ->

                            _sellSuccess.value = SellUIState.Success(it)
                        }
                    }
                    is Resource.Error -> {
                        Timber.i("HAOHAO : Resource.Error ${result.message}")

                        _sellSuccess.value = SellUIState.Error(result.message!!)
                    }
                    else -> {}
                }

            }.launchIn(viewModelScope)
        }
    }
}
