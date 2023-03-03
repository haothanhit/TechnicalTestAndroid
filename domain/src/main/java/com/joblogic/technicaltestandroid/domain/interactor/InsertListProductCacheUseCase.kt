package com.joblogic.technicaltestandroid.domain.interactor

import com.joblogic.technicaltestandroid.domain.Resource
import com.joblogic.technicaltestandroid.domain.models.ProductModel
import com.joblogic.technicaltestandroid.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias InsertListProductCacheBaseUseCase = BaseUseCase<ProductModel, Flow<Resource<Unit>>>

class InsertListProductCacheUseCase @Inject constructor(
    private val mainRepository: MainRepository
) : InsertListProductCacheBaseUseCase {

    override suspend fun execute(params: ProductModel) = mainRepository.insert(params)
}
