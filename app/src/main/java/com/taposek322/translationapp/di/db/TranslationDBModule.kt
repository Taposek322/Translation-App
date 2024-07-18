package com.taposek322.translationapp.di.db

import android.content.Context
import androidx.room.Room
import com.taposek322.translationapp.data.db.room.TranslationDataBaseObject
import com.taposek322.translationapp.data.db.room.TranslationHistoryDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TranslationDBModule {
    @Provides
    @Singleton
    fun providesTranslationHistoryDB(context: Context):TranslationHistoryDB{
        return Room.databaseBuilder(context, TranslationHistoryDB::class.java,
            TranslationDataBaseObject.databaseName
        ).build()
    }
}