package com.taposek322.translationapp.di.remote

import com.taposek322.translationapp.data.remote.skyengApi.SkyengTranslatorApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class SkyengTranslatorModule{
    @Provides
    fun provideSkyengTranslator(): SkyengTranslatorApi {
        return Retrofit.Builder()
            .baseUrl("https://dictionary.skyeng.ru")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(SkyengTranslatorApi::class.java)
    }
}