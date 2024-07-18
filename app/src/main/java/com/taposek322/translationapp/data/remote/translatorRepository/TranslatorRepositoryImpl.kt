package com.taposek322.translationapp.data.remote.translatorRepository

import android.util.Log
import com.taposek322.translationapp.R
import com.taposek322.translationapp.data.remote.mapper.toTranslationData
import com.taposek322.translationapp.data.remote.skyengApi.SkyengTranslatorApi
import com.taposek322.translationapp.domain.translation.TranslationData
import com.taposek322.translationapp.domain.translatorRepository.TranslatorRepository
import com.taposek322.translationapp.domain.util.Resource
import com.taposek322.translationapp.presentation.util.UiText
import javax.inject.Inject
import javax.inject.Singleton

private const val tag = "TranslatorRepositoryImpl"

@Singleton
class TranslatorRepositoryImpl @Inject constructor(
    private val skyengApi: SkyengTranslatorApi
):TranslatorRepository {
    override suspend fun translate(word: String): Resource<TranslationData> {
        return try {
            Log.d(tag,"in translate")
            val data = skyengApi.translate(word)
            if(data.isEmpty()){
                Resource.Error(UiText.StringResource(R.string.unknown_definition_error))
            }else {
                Resource.Success(data = data.first().toTranslationData())
            }
        }catch (ex:Exception){
            Resource.Error(
                message = if(ex.message !=null){
                    UiText.DynamicString(ex.message!!)
                }else{
                    UiText.StringResource(R.string.unknown_error)
                }
            )
        }
    }
}