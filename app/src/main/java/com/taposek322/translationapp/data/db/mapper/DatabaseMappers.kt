package com.taposek322.translationapp.data.db.mapper

import com.taposek322.translationapp.data.db.room.TranslationEntity
import com.taposek322.translationapp.domain.dbData.HistoryData
import com.taposek322.translationapp.domain.translation.TranslationData


fun TranslationEntity.toHistoryData():HistoryData{
    val translationData = TranslationData(
        text = this.text,
        transcription = this.transcription,
        partOfSpeech = this.partOfSpeech,
        translation = this.translation,
    )
    return HistoryData(
        id = this.id,
        translationData = translationData,
        favourite = this.favourite
    )
}

fun HistoryData.toTranslationEntity():TranslationEntity {
    return TranslationEntity(
        id = this.id,
        text = this.translationData.text,
        transcription = this.translationData.transcription,
        partOfSpeech = this.translationData.partOfSpeech,
        translation = this.translationData.translation,
        favourite = this.favourite,
    )
}