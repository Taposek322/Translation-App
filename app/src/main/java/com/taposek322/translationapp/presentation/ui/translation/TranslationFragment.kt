package com.taposek322.translationapp.presentation.ui.translation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.taposek322.translationapp.R
import com.taposek322.translationapp.appComponent
import com.taposek322.translationapp.databinding.TranslationScreenBinding
import com.taposek322.translationapp.presentation.ui.recyclerView.HistoryAdapter
import com.taposek322.translationapp.presentation.ui.recyclerView.SwipeToDeleteCallback
import com.taposek322.translationapp.presentation.util.ViewModelFactory
import javax.inject.Inject

class TranslationFragment:Fragment() {

    private var _binding: TranslationScreenBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            getString(R.string.cant_find_binding_error)
        }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: TranslationViewModel

    override fun onAttach(context: Context) {
        context.appComponent.injectToTranslationFragment(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this,viewModelFactory)[TranslationViewModel::class.java]
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TranslationScreenBinding.inflate(layoutInflater,container,false)
        binding.fullHistoryRecyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.historyList.observe(viewLifecycleOwner) { historyList ->
            val adapter = HistoryAdapter(
                historyList = historyList,
                onFavouriteButtonClick = viewModel::updateFavourite,
                onRemove = viewModel::deleteTranslationHistory
            )
            binding.fullHistoryRecyclerView.adapter = adapter

            val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(adapter))
            itemTouchHelper.attachToRecyclerView(binding.fullHistoryRecyclerView)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.translationLoading.visibility = View.VISIBLE
                binding.translatedContainer.visibility = View.INVISIBLE
            } else {
                binding.translationLoading.visibility = View.INVISIBLE
                binding.translatedContainer.visibility = View.VISIBLE
            }
        }
        viewModel.translatedText.observe(viewLifecycleOwner) { translationData ->
            binding.apply {
                translationSeparator.visibility =
                    if (translationData == null) View.GONE else View.VISIBLE
                englishTextView.text = translationData?.text
                transcriptionTextView.text = translationData?.transcription
                partOfSpeechTextView.text = translationData?.partOfSpeech?.part
                translatedTextTextView.text = translationData?.translation
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let { message ->
                Toast.makeText(context, context?.let { message.asString(it) }, Toast.LENGTH_LONG)
                    .show()
                viewModel.clearErrorMessage()
            }

        }

        viewModel.perhapsMeaning.observe(viewLifecycleOwner) { perhapsMeaning ->
            if (perhapsMeaning) {
                binding.perhapsMeanTextView.visibility = View.VISIBLE
            } else {
                binding.perhapsMeanTextView.visibility = View.GONE
            }

        }
        binding.apply {
            translationEditText.doOnTextChanged { text, _, _, _ ->
                viewModel.updateTextToTranslate(text.toString())
            }
            translateButton.setOnClickListener{
                viewModel.translate()
            }
        }
    }

    override fun onStart() {
        viewModel.displayHistoryList()
        super.onStart()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}