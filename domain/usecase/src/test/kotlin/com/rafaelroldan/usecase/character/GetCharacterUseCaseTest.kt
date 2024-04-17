package com.rafaelroldan.usecase.character

import com.rafaelroldan.dto.result.Result
import com.rafaelroldan.mappers.character.CharacterMapper
import com.rafaelroldan.model.CharacterModel
import com.rafaelroldan.repository.character.CharacterRepository
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
class GetCharacterUseCaseTest: BaseTestCoroutine(){

    private lateinit var useCase: GetCharacterUseCase

    @RelaxedMockK
    private lateinit var mapper: CharacterMapper

    @RelaxedMockK
    private lateinit var repository: CharacterRepository

    @Before
    override fun setUp() {
        super.setUp()
        MockKAnnotations.init(this)
        useCase = spyk(
            GetCharacterUseCaseImpl(
                repository,mapper
            ), recordPrivateCalls = true
        )
    }

    @Test
    fun `when we try get a character with pagination`() =
        runTest {
            val response = Result<CharacterModel>(200, false , null)

            coEvery { useCase.getAllCharacter(any(),any()) } returns flow {
                emit(response)
            }

            var result: Result<CharacterModel>? = null
            useCase.getAllCharacter(0,20).collect{
                result = it
            }
            assertEquals( response, result )
        }

    @Test
    fun `when we try get a character with id`() =
        runTest {
            val response = Result<CharacterModel>(200, false , null)

            coEvery { useCase.getCharacterById(any()) } returns flow {
                emit(response)
            }

            var result: Result<CharacterModel>? = null
            useCase.getCharacterById(0).collect{
                result = it
            }
            assertEquals( response, result )
        }

    @Test
    fun `when we try get a character with name`() =
        runTest {
            val response = Result<CharacterModel>(200, false , null)

            coEvery { useCase.getCharacterByStartName(any(),any(), any()) } returns flow {
                emit(response)
            }

            var result: Result<CharacterModel>? = null
            useCase.getCharacterByStartName(0,20, "name").collect{
                result = it
            }
            assertEquals( response, result )
        }
}
