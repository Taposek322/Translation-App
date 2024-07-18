package com.taposek322.translationapp.presentation.ui.translation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.taposek322.translationapp.domain.dbData.HistoryData
import com.taposek322.translationapp.domain.dbRepository.DataBaseRepository
import com.taposek322.translationapp.domain.translation.TranslationData
import com.taposek322.translationapp.domain.translatorRepository.TranslatorRepository
import com.taposek322.translationapp.domain.util.Resource
import com.taposek322.translationapp.presentation.util.UiText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TranslationViewModel @Inject constructor(
    private val translationRepository: TranslatorRepository,
    private val dbRepository: DataBaseRepository
) : ViewModel() {

    private var _historyList = MutableLiveData<List<HistoryData>>()
    val historyList : LiveData<List<HistoryData>>
        get() = _historyList.map {
            it.toList()
        }

    private var _textToTranslate: String = ""

    private var _translatedText = MutableLiveData<TranslationData?>(null)
    val translatedText: LiveData<TranslationData?>
        get() = _translatedText

    private var _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private var _perhapsMeaning = MutableLiveData(false)
    val perhapsMeaning : LiveData<Boolean>
        get() = _perhapsMeaning


    private var _errorMessage = MutableLiveData<UiText?>(null)
    val errorMessage:LiveData<UiText?>
        get() = _errorMessage


    fun updateTextToTranslate(text: String) {
        _textToTranslate = text
    }

    fun translate(){
        _loading.value = true
        _translatedText.value = null
        _perhapsMeaning.value = false
        var successResult = false
        _textToTranslate = _textToTranslate.trim()
        viewModelScope.launch(Dispatchers.IO) {
            val result = translationRepository.translate(_textToTranslate)
            withContext(Dispatchers.Main){
                when (result){
                    is Resource.Success-> {
                        _translatedText.value = result.data
                        _perhapsMeaning.value = translatedText.value?.text != _textToTranslate
                        successResult = true
                    }
                    is Resource.Error -> {
                        _errorMessage.value = result.message
                    }
                }
                _loading.value = false
            }
            if(successResult) {
                withContext(Dispatchers.IO) {
                    translatedText.value?.let {
                        if (!dbRepository.findTranslation(it.text)) {
                            addTranslationToDb()
                            getHistoryList()
                        }
                    }
                }
            }
        }
    }

    fun updateFavourite(position: Int){
        historyList.value?.let {dataList ->
            val mutableHistoryList = dataList.toMutableList()

            mutableHistoryList[position] = mutableHistoryList[position].copy(favourite = !mutableHistoryList[position].favourite)
            _historyList.value = mutableHistoryList.toList()
            CoroutineScope(Dispatchers.IO).launch {
                dbRepository.updateTranslation(historyList.value!![position])
            }
        }

    }

    fun deleteTranslationHistory(position: Int){
        historyList.value?.let {hl->
            viewModelScope.launch(Dispatchers.IO) {
                dbRepository.deleteTranslation(hl[position])
                getHistoryList()
            }
        }
    }

    private suspend fun addTranslationToDb(){
        translatedText.value?.let {translationData->
            val historyData = HistoryData(
                id = 0,
                translationData = translationData,
                favourite = false
            )
            dbRepository.insertTranslation(historyData)
        }
    }

    fun displayHistoryList(){
        viewModelScope.launch(Dispatchers.IO){
            getHistoryList()
        }
    }

    private suspend fun getHistoryList(){
        CoroutineScope(Dispatchers.IO).launch {
            val result = dbRepository.getAllTranslation()
            withContext(Dispatchers.Main) {
                _historyList.value = result
            }
        }
    }

    fun clearErrorMessage(){
        _errorMessage.value = null
    }
}