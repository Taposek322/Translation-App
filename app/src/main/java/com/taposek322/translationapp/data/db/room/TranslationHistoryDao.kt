package com.taposek322.translationapp.data.db.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TranslationHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTranslation(translationEntity: TranslationEntity):Long

    @Update
    fun updateTranslation(translationEntity: TranslationEntity)

    @Delete
    fun deleteTranslation(translationEntity: TranslationEntity)

    @Query("SELECT * FROM translationHistory ORDER BY id DESC")
    fun getAllTranslation():List<TranslationEntity>

    @Query("SELECT * FROM translationHistory WHERE favourite=1 ORDER BY id DESC")
    fun getFavouriteTranslation():List<TranslationEntity>

    @Query("SELECT * FROM translationHistory WHERE text = :text")
    fun findTranslation(text:String):TranslationEntity?
}