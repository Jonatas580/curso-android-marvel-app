package com.example.marvelapp.framework.remote

import com.example.marvelapp.framework.network.MarvelApi
import com.example.marvelapp.framework.network.response.DataWrapperResponse
import com.jonatas.core.data.repository.CharactersRemoteDataSource
import javax.inject.Inject

class RetrofitCharactersDataSouce @Inject constructor(
    private val marvelApi: MarvelApi
) : CharactersRemoteDataSource< DataWrapperResponse> {

    override suspend fun fetchCharacters(queries: Map<String, String>): DataWrapperResponse {
      return marvelApi.getCharacters(queries)
    }
}