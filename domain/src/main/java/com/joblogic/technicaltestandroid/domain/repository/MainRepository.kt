package com.joblogic.technicaltestandroid.domain.repository

import com.joblogic.technicaltestandroid.domain.Resource
import com.joblogic.technicaltestandroid.domain.models.ProductModel
import com.joblogic.technicaltestandroid.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getListProduct(): Flow<Resource<List<ProductModel>>>

    suspend fun getListUser(): Flow<Resource<List<UserModel>>>

    suspend fun getListCacheProduct(): Flow<Resource<List<ProductModel>>>

    suspend fun insert(productResponse:ProductModel): Flow<Resource<Unit>>

}
