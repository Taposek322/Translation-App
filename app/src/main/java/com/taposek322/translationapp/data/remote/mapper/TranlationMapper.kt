package com.taposek322.translationapp.data.remote.mapper

import com.taposek322.translationapp.data.remote.skyengApi.response.Search
import com.taposek322.translationapp.domain.translation.toPartOfSpeech
import com.taposek322.translationapp.domain.translation.TranslationData

fun Search.toTranslationData():TranslationData{
    return TranslationData(
        text = this.text,
        transcription = this.meanings.first().transcription,
        partOfSpeech = toPartOfSpeech(this.meanings.first().partOfSpeechCode),
        translation = this.meanings.first().translation.text
    )
}
