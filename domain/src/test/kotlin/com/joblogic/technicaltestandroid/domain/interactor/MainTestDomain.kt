package com.joblogic.technicaltestandroid.domain.interactor

import com.joblogic.technicaltestandroid.domain.fakes.FakeData
import com.joblogic.technicaltestandroid.domain.repository.MainRepository
import com.joblogic.technicaltestandroid.domain.utils.DomainBaseTest
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainTestDomain : DomainBaseTest() {
    @Mock
    lateinit var mainRepository: MainRepository

    lateinit var sut: ListProductUseCase

    @Before
    fun setUp() {
        sut = ListProductUseCase(mainRepository)
    }

    @Test
    fun `set use case list user should return success with response code`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            whenever(mainRepository.getListProduct()) doReturn FakeData.getProducts()

            // Act (When)
            val status = sut.single()

            // Assert (Then)
            assertEquals(status, 2)
            verify(mainRepository, times(1)).getListUser()
        }


}
