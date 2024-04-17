package com.rafaelroldan.usecase.comic

import com.rafaelroldan.dto.result.Result
import com.rafaelroldan.mappers.comic.ComicMapper
import com.rafaelroldan.model.ComicModel
import com.rafaelroldan.repository.comic.ComicRepository
import com.rafaelroldan.usecase.BaseTestCoroutine
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.spyk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.Test

@RunWith(JUnit4::class)
class GetComicUseCaseTest: BaseTestCoroutine(){

    private lateinit var useCase: GetComicUseCase

    @RelaxedMockK
    private lateinit var mapper: ComicMapper

    @RelaxedMockK
    private lateinit var repository: ComicRepository

    @Before
    override fun setUp() {
        super.setUp()
        MockKAnnotations.init(this)
        useCase = spyk(
            GetComicUseCaseImpl(
                repository,mapper
            ), recordPrivateCalls = true
        )
    }

    @Test
    fun `when we try get list comic from a character`() =
        runTest {
            val response = Result<ComicModel>(200, false , null)

            coEvery { useCase.getComicByCharacter(any()) } returns flow {
                emit(response)
            }

            var result: Result<ComicModel>? = null
            useCase.getComicByCharacter(0).collect{
                result = it
            }
            assertEquals( response, result )
        }
}
