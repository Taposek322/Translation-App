package com.taposek322.translationapp.data.db.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.taposek322.translationapp.domain.translation.PartOfSpeech

@Entity(tableName = "translationHistory")
data class TranslationEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0,
    val text:String,
    val transcription:String,
    val partOfSpeech: PartOfSpeech,
    val translation:String,
    val favourite:Boolean = false
)