package com.taposek322.translationapp.domain.translatorRepository

import com.taposek322.translationapp.domain.translation.TranslationData
import com.taposek322.translationapp.domain.util.Resource

interface TranslatorRepository {
    suspend fun translate(word:String):Resource<TranslationData>
}