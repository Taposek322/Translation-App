package com.taposek322.translationapp.presentation.ui.translationhistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taposek322.translationapp.domain.dbData.HistoryData
import com.taposek322.translationapp.domain.dbRepository.DataBaseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TranslationHistoryViewModel @Inject constructor(
    private val dbRepository: DataBaseRepository
):ViewModel() {
    private var _historyList = MutableLiveData<List<HistoryData>>(listOf())
    val historyList : LiveData<List<HistoryData>>
        get() = _historyList

    private var _refreshing = MutableLiveData<Boolean>(false)
    val refreshing:LiveData<Boolean>
        get() = _refreshing


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

    fun refreshHistoryList(){
        _refreshing.value = true
        CoroutineScope(Dispatchers.IO).launch {
            getFavouriteHistoryList()
            withContext(Dispatchers.Main) {
                _refreshing.value = false
            }
        }
    }

    fun deleteTranslationHistory(position: Int){
        historyList.value?.let {hl->
            viewModelScope.launch(Dispatchers.IO) {
                dbRepository.deleteTranslation(hl[position])
                getFavouriteHistoryList()
            }
        }
    }

    fun displayFavouriteHistoryList(){
        viewModelScope.launch(Dispatchers.IO) {
            getFavouriteHistoryList()
        }
    }

    private suspend fun getFavouriteHistoryList(){
        val result = dbRepository.getFavouriteTranslation()
        withContext(Dispatchers.Main) {
            _historyList.value = result
        }
    }
}