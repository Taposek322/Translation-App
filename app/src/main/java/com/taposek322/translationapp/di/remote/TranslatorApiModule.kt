package com.taposek322.translationapp.di.remote

import com.taposek322.translationapp.data.remote.translatorRepository.TranslatorRepositoryImpl
import com.taposek322.translationapp.domain.translatorRepository.TranslatorRepository
import dagger.Binds
import dagger.Module

@Module(includes = [SkyengTranslatorModule::class])
abstract class TranslatorApiModule {

    @Binds
    abstract fun bindTranslatorRepository(translatorRepositoryImpl: TranslatorRepositoryImpl):TranslatorRepository
}
