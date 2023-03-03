package com.joblogic.technicaltestandroid.data.repository

import com.joblogic.technicaltestandroid.data.api.MainService
import com.joblogic.technicaltestandroid.data.mapper.ProductLocationEntityMapper
import com.joblogic.technicaltestandroid.data.mapper.ProductEntityMapper
import com.joblogic.technicaltestandroid.data.mapper.UserEntityMapper
import com.joblogic.technicaltestandroid.domain.Resource
import com.joblogic.technicaltestandroid.domain.models.ProductModel
import com.joblogic.technicaltestandroid.domain.models.UserModel
import com.joblogic.technicaltestandroid.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(
    private val userService: MainService,
    private val userEntityMapper: UserEntityMapper,
    private val productEntityMapper: ProductEntityMapper,
    private val mainCache: MainCache,
    private val productLocationEntityMapper: ProductLocationEntityMapper

    ) : MainRepository {


    override suspend fun getListProduct(): Flow<Resource<List<ProductModel>>> {
        return flow {
            emit(Resource.Loading())
            val list = userService.getListProduct().map {
                productEntityMapper.mapFromModel(it)
            }
            emit(Resource.Success(list))
        }.catch { emit(Resource.Error(it.message.toString())) }
    }

    override suspend fun getListUser(): Flow<Resource<List<UserModel>>> {
        return flow {
            emit(Resource.Loading())
            val list = userService.getListUser().map {
                userEntityMapper.mapFromModel(it)
            }
            emit(Resource.Success(list))
        }.catch { emit(Resource.Error(it.message.toString())) }
    }

    override suspend fun getListCacheProduct(): Flow<Resource<List<ProductModel>>> {
        return flow {
            emit(Resource.Loading())
            val list = mainCache.getProducts().map {
                productEntityMapper.mapFromModel(it)
            }
            emit(Resource.Success(list))
        }.catch { emit(Resource.Error(it.message.toString())) }


    }

    override suspend fun insert(productResponse:ProductModel): Flow<Resource<Unit>> {
     return   flow {
            emit(Resource.Loading())
            val list = mainCache.insert(productLocationEntityMapper.mapFromModel(productResponse))
            emit(Resource.Success(list))
        }.catch { emit(Resource.Error(it.message.toString())) }
    }


}
