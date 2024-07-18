package com.taposek322.translationapp.data.remote.skyengApi

import com.taposek322.translationapp.data.remote.skyengApi.response.Search
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface SkyengTranslatorApi {
    @GET("/api/public/v1/words/search")
    suspend fun translate(
        @Query("search") search:String
    ):List<Search>

}