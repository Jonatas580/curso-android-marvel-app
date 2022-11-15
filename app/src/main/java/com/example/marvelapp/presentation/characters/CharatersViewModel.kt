package com.example.marvelapp.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jonatas.core.usecase.GetCharactersUseCase
import kotlinx.coroutines.flow.Flow
import com.jonatas.core.domain.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharatersViewModel @Inject constructor(
   private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

   fun charactersPagingData(query: String): Flow<PagingData<Character>> {
      return getCharactersUseCase(
         GetCharactersUseCase.GetCharactersParms(query, getPageConfig())
      ).cachedIn(viewModelScope)
   }

   private fun getPageConfig() = PagingConfig(
      pageSize = 20
   )
}