package com.joblogic.technicaltestandroid.data.mapper

import com.joblogic.technicaltestandroid.data.reponse.ProductResponse
import com.joblogic.technicaltestandroid.domain.models.ProductModel
import javax.inject.Inject

class ProductEntityMapper @Inject constructor() :
    EntityMapper<ProductResponse, ProductModel> {

    override fun mapFromModel(model: ProductResponse): ProductModel {
        return ProductModel(
            id = model.id,
            name = model.name,
            price = model.price,
            quantity = model.quantity,
            type = model.type
        )
    }
}
