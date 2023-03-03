package com.joblogic.technicaltestandroid.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joblogic.technicaltestandroid.cache.models.ProductCacheEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM ItemToSell")
    fun getProducts(): List<ProductCacheEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg character: ProductCacheEntity)
}
