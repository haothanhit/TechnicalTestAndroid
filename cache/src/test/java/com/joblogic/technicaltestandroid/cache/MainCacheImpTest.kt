package com.joblogic.technicaltestandroid.cache

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.joblogic.technicaltestandroid.cache.fakes.FakeCacheData
import com.joblogic.technicaltestandroid.cache.mapper.ProductCacheMapper
import com.joblogic.technicaltestandroid.cache.utils.CacheBaseTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@Config(manifest = Config.NONE)
@RunWith(AndroidJUnit4::class)
class MainCacheImpTest : CacheBaseTest() {
    private val productCacheMapper = ProductCacheMapper()
    lateinit var sut: MainCacheImp

    @Before
    override fun setup() {
        super.setup()
        sut = MainCacheImp(productDao, productCacheMapper)
    }

    @Test
    fun `get products should return success products from local room cache`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val productsEntity = FakeCacheData.getFakeProductEntity(10)
            sut.inserts(productsEntity)

            // Saving characters to database so we can get it when select query executes
//            productsEntity.map {
//
//            }
            // Act (When)
            val products = sut.getProducts()
            // Assert (Then)
            assertEquals(products.size, 10)
        }


}
