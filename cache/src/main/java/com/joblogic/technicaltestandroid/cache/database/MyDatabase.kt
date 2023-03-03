package com.joblogic.technicaltestandroid.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.joblogic.technicaltestandroid.cache.dao.ProductDao
import com.joblogic.technicaltestandroid.cache.models.ProductCacheEntity
import com.joblogic.technicaltestandroid.cache.utils.CacheConstants
import com.joblogic.technicaltestandroid.cache.utils.Migrations
import javax.inject.Inject

@Database(
    entities = [ProductCacheEntity::class],
    version = Migrations.DB_VERSION,
    exportSchema = false
)
abstract class MyDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedProductDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MyDatabase::class.java,
            CacheConstants.DB_NAME
        ).allowMainThreadQueries().build()
    }
}
