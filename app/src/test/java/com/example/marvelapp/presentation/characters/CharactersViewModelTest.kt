package com.example.marvelapp.presentation.characters

import androidx.paging.PagingData
import com.jonatas.core.domain.model.Character
import com.jonatas.core.usecase.GetCharactersUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharactersViewModelTest {

    @ExperimentalCoroutinesApi
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher() //Classe obsoleta
//    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher() //Classes novas para utilização dos dispatcher
//    val testDispatcher: TestDispatcher = StandardTestDispatcher()

    @Mock
    lateinit var getCharactersUseCase: GetCharactersUseCase

//NAÕ MOCKAR A CLASSE QUE EU QUERO TESTAR E SIM AS DEPENDENCIAS DELA
    private  lateinit var charactersViewModel: CharactersViewModel

    private val pagingDataCharacters = PagingData.from(
        listOf(
            Character(
                "3-D Man",
                "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            ),
            Character(
                "A-Bomb (HAS)",
                "https://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg"
            )
        )
    )

//EXECUTA ANTES DAS CLASSES DE TESTE.
    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        charactersViewModel = CharactersViewModel(getCharactersUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test                                                                                       //runBlockingTest
    fun `should validate the paging data object values when calling charactersPagingData `() = runBlockingTest {
        whenever(
            getCharactersUseCase.invoke(any())
            ).thenReturn(
                flowOf(
                    pagingDataCharacters
                )
            )


        val result = charactersViewModel.charactersPagingData("")

        assertEquals(1, result.count())
    }
}