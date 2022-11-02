package com.jonatas.core.data.network

import com.jonatas.core.data.network.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MarvelApi {

    @GET("characters")
    suspend fun getCharacters (@QueryMap queries: Map<String, String>) : DataWrapperResponse
}