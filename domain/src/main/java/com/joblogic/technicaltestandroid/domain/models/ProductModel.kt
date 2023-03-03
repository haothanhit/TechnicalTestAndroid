package com.joblogic.technicaltestandroid.domain.models

data class ProductModel(
    val id: Int,
    val name: String,
    val price: Int,
    val quantity: Int,
    val type: Int
){
    override fun toString(): String {
        return "Name: $name\n\nPrice: $price\n\nQuantity: $quantity"
    }
}
