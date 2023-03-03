package com.joblogic.technicaltestandroid.domain.interactor

import com.joblogic.technicaltestandroid.domain.Resource
import com.joblogic.technicaltestandroid.domain.models.ProductModel
import com.joblogic.technicaltestandroid.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias ListProductBaseUseCase = BaseUseCase<Unit, Flow<Resource<List<ProductModel>>>>

class ListProductUseCase @Inject constructor(
    private val mainRepository: MainRepository
) : ListProductBaseUseCase {

    override suspend fun execute(params: Unit) = mainRepository.getListProduct()
}
