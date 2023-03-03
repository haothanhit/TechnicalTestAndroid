package com.joblogic.technicaltestandroid.data.mapper

import com.joblogic.technicaltestandroid.data.fakes.FakeProducts
import com.joblogic.technicaltestandroid.data.utils.DataBaseTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ProductMapperTest : DataBaseTest() {

    lateinit var sut: ProductEntityMapper

    @Before
    fun setUp() {
        sut = ProductEntityMapper()
    }

    @Test
    fun `map  Product entity to location should return converted location`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val name = FakeProducts.getProducts()[0]

            // Act (When)
            val locationMapper = sut.mapFromModel(name)

            // Assert (Then)
            assertEquals(locationMapper.name, name.name)
        }


}
