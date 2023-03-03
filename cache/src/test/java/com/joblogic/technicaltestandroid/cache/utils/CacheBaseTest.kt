package com.joblogic.technicaltestandroid.cache.utils

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.joblogic.technicaltestandroid.cache.dao.ProductDao
import com.joblogic.technicaltestandroid.cache.database.MyDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import java.io.IOException

@ExperimentalCoroutinesApi
abstract class CacheBaseTest {

    /**
     * A test rule to allow testing coroutines that use the main dispatcher
     */
    @get:Rule
    val testRule = CoroutineTestRule()

    val dispatcher = testRule.dispatcher
    private lateinit var database: MyDatabase
    protected lateinit var productDao: ProductDao
    protected lateinit var context: Context

    @Before
    open fun setup() {
        context = ApplicationProvider.getApplicationContext()
        database =
            Room.inMemoryDatabaseBuilder(context, MyDatabase::class.java).allowMainThreadQueries().build()
        productDao = database.cachedProductDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        dispatcher.runBlockingTest {
            database.clearAllTables()
        }
        database.close()
    }
}
