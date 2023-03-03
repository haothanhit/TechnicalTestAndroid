package com.joblogic.technicaltestandroid.cache

import com.joblogic.technicaltestandroid.cache.dao.ProductDao
import com.joblogic.technicaltestandroid.cache.mapper.ProductCacheMapper
import com.joblogic.technicaltestandroid.data.reponse.ProductResponse
import com.joblogic.technicaltestandroid.data.repository.MainCache
import javax.inject.Inject

class MainCacheImp @Inject constructor(
    private val productDao: ProductDao,
    private val productCacheMapper: ProductCacheMapper,
) : MainCache {


    override suspend fun getProducts(): List<ProductResponse> {
        return productDao.getProducts().map { cacheProduct ->
            productCacheMapper.mapFromCached(cacheProduct)
        }
    }

    override suspend fun insert(productResponse:ProductResponse) {
        productDao.insert(productCacheMapper.mapToCached(productResponse))
    }
    override suspend fun inserts(list: List<ProductResponse>) {
        productDao.insert(
            *list.map {
                productCacheMapper.mapToCached(it)
            }.toTypedArray()
        )
    }
}
