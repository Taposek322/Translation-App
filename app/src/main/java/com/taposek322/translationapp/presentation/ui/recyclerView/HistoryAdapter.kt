package com.taposek322.translationapp.presentation.ui.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.taposek322.translationapp.R
import com.taposek322.translationapp.databinding.TranslationResultBinding
import com.taposek322.translationapp.domain.dbData.HistoryData


class HistoryAdapter(
    private val historyList:List<HistoryData>,
    private val onFavouriteButtonClick: (Int)->Unit,
    private val onRemove:(Int)->Unit
):RecyclerView.Adapter<HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TranslationResultBinding.inflate(inflater,parent,false)

        return HistoryViewHolder(binding)
    }

    override fun getItemCount(): Int = historyList.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val historyRes = historyList[position]
        val context = holder.itemView.context
        holder.binding.apply {
            englishTextView.text = historyRes.translationData.text
            transcriptionTextView.text = historyRes.translationData.transcription
            partOfSpeechTextView.text = historyRes.translationData.partOfSpeech.part
            translatedTextTextView.text = historyRes.translationData.translation
            if(historyRes.favourite){
                FavouriteButton.background = AppCompatResources.getDrawable(context,R.drawable.baseline_star_favourite_24)
                FavouriteButton.contentDescription = context.getText(R.string.delete_from_favourites)
            }else{
                FavouriteButton.background = AppCompatResources.getDrawable(context,R.drawable.baseline_star_border_24)
                FavouriteButton.contentDescription = context.getText(R.string.add_to_favourites)
            }
            FavouriteButton.setOnClickListener {
                onFavouriteButtonClick(position)
            }
        }
    }

    fun removeItem(position: Int){
        onRemove(position)
    }
}