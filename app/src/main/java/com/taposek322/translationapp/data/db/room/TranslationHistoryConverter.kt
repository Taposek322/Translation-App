package com.taposek322.translationapp.data.db.room

import androidx.room.TypeConverter
import com.taposek322.translationapp.domain.translation.PartOfSpeech


class TranslationHistoryConverter {
    @TypeConverter
    fun toPartOfSpeech(value:String) = PartOfSpeech.entries.find { it.part == value }

    @TypeConverter
    fun fromPartOfSpeech(value:PartOfSpeech) = value.part
}