package com.jonatas.core.data.repository

import androidx.paging.PagingSource
import com.jonatas.core.domain.model.Character

interface CharactersRepository {

    fun getCharacters(query: String): PagingSource<Int, Character>

}