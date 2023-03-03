package com.joblogic.technicaltestandroid.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joblogic.technicaltestandroid.cache.utils.CacheConstants

@Entity(tableName = CacheConstants.PRODUCT_TABLE_NAME)
data class ProductCacheEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Int,
    val quantity: Int,
    val type: Int
)
