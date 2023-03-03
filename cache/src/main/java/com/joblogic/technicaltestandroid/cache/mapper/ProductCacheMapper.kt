package com.joblogic.technicaltestandroid.cache.mapper

import com.joblogic.technicaltestandroid.cache.models.ProductCacheEntity
import com.joblogic.technicaltestandroid.data.reponse.ProductResponse
import javax.inject.Inject

class ProductCacheMapper @Inject constructor() : CacheMapper<ProductCacheEntity, ProductResponse> {
    override fun mapFromCached(type: ProductCacheEntity): ProductResponse {
        return ProductResponse(
            id = type.id,
            price = type.price,
            quantity = type.quantity,
            name = type.name,
            type = type.type
        )
    }

    override fun mapToCached(type: ProductResponse): ProductCacheEntity {
        return ProductCacheEntity(
            id = type.id,
            price = type.price,
            quantity = type.quantity,
            name = type.name,
            type = type.type
        )
    }

}
