package com.joblogic.technicaltestandroid.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.joblogic.technicaltestandroid.domain.Resource
import com.joblogic.technicaltestandroid.domain.interactor.InsertListProductCacheUseCase
import com.joblogic.technicaltestandroid.domain.interactor.ListProductUseCase
import com.joblogic.technicaltestandroid.domain.interactor.ListUserUseCase
import com.joblogic.technicaltestandroid.domain.models.ProductModel
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

sealed class BuyUIState {
    data class Loading(val isLoading: Boolean = false) : BuyUIState()
    data class Error(var error: String = "") : BuyUIState()
    data class Success(val data: List<ProductModel>) : BuyUIState()
}


@HiltViewModel
class BuyViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val listProductUseCase: ListProductUseCase,
    private val insertListProductCacheUseCase: InsertListProductCacheUseCase
) : BaseViewModel(contextProvider) {

    private val _buySuccess = MutableStateFlow<BuyUIState>(BuyUIState.Loading(false))
    val buySuccess = _buySuccess.asStateFlow()

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.d("Error: ${exception.message}")
    }
    init {
        getListProduct()
    }

   private fun getListProduct() {
        launchCoroutineIO {
            listProductUseCase.execute(Unit).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _buySuccess.value = BuyUIState.Loading(true)
                    }
                    is Resource.Success -> {
                        result.data?.let { it ->

                            _buySuccess.value = BuyUIState.Success(it)
                        }
                    }
                    is Resource.Error -> {
                        _buySuccess.value = BuyUIState.Error(result.message!!)
                    }
                }

            }.launchIn(viewModelScope)
        }
    }
     fun insertAllListToDatabase(params: ProductModel) {
        launchCoroutineIO {
            insertListProductCacheUseCase.execute(params).launchIn(viewModelScope)
        }
    }
}
