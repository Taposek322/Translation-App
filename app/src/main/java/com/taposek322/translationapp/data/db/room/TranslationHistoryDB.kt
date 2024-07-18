package com.taposek322.translationapp.data.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [TranslationEntity::class], version = 1)
@TypeConverters(TranslationHistoryConverter::class)
abstract class TranslationHistoryDB:RoomDatabase() {
    abstract fun translationDao(): TranslationHistoryDao
}