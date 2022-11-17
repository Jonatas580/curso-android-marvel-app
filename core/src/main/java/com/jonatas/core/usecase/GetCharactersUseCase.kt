package com.jonatas.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jonatas.core.data.repository.CharactersRepository
import com.jonatas.core.domain.model.Character
import com.jonatas.core.usecase.GetCharactersUseCase.GetCharactersParams
import com.jonatas.core.usecase.base.PadingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCharactersUseCase {
    operator fun invoke(params: GetCharactersParams): Flow<PagingData<Character>>

    data class GetCharactersParams(val query: String, val pagingConfig: PagingConfig)
}

class GetCharactersUseCaseImpl @Inject constructor(
private val charactersRepository: CharactersRepository
) : PadingUseCase<GetCharactersParams, Character>(),
    GetCharactersUseCase {

    override fun createFlowObservable(params: GetCharactersParams): Flow<PagingData<Character>> {
        return Pager(config = params.pagingConfig) {
            charactersRepository.getCharacters(params.query)
        }.flow
    }
}