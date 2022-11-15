package com.jonatas.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jonatas.core.data.repository.CharactersRepository
import com.jonatas.core.usecase.base.PadingUseCase
import com.jonatas.core.domain.model.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
private val charactersRepository: CharactersRepository
) : PadingUseCase<GetCharactersUseCase.GetCharactersParms, Character>() {

    override fun createFlowObservable(params: GetCharactersParms): Flow<PagingData<Character>> {
        return Pager(config = params.pagingConfig) {
            charactersRepository.getCharacters(params.query)
        }.flow
    }

    data class GetCharactersParms(val query: String, val pagingConfig: PagingConfig)
}