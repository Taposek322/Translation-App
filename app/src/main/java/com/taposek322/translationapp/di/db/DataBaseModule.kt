package com.taposek322.translationapp.di.db

import com.taposek322.translationapp.data.db.repository.DataBaseRepositoryImpl
import com.taposek322.translationapp.domain.dbRepository.DataBaseRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [TranslationDBModule::class])
interface DataBaseModule {

    @Binds
    @Singleton
    fun bindsDataBaseRepository(dataBaseRepositoryImpl: DataBaseRepositoryImpl):DataBaseRepository
}