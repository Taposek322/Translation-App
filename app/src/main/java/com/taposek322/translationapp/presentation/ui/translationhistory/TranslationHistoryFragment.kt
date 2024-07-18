package com.taposek322.translationapp.presentation.ui.translationhistory

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.taposek322.translationapp.R
import com.taposek322.translationapp.appComponent
import com.taposek322.translationapp.databinding.TranslationHistoryBinding
import com.taposek322.translationapp.presentation.ui.recyclerView.HistoryAdapter
import com.taposek322.translationapp.presentation.ui.recyclerView.SwipeToDeleteCallback
import com.taposek322.translationapp.presentation.util.ViewModelFactory
import javax.inject.Inject

class TranslationHistoryFragment:Fragment() {

    private var _binding: TranslationHistoryBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            getString(R.string.cant_find_binding_error)
        }


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: TranslationHistoryViewModel

    override fun onAttach(context: Context) {
        context.appComponent.injectToTranslationHistoryFragment(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this,viewModelFactory)[TranslationHistoryViewModel::class.java]
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TranslationHistoryBinding.inflate(inflater,container,false)
        binding.historyRecyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.historyList.observe(viewLifecycleOwner) { historyList ->
            val adapter = HistoryAdapter(
                historyList = historyList,
                onFavouriteButtonClick = viewModel::updateFavourite,
                onRemove = viewModel::deleteTranslationHistory
            )
            binding.historyRecyclerView.adapter = adapter

            val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(adapter))
            itemTouchHelper.attachToRecyclerView(binding.historyRecyclerView)
        }
        viewModel.refreshing.observe(viewLifecycleOwner) { refreshing ->
            binding.swipeToRefresh.isRefreshing = refreshing
        }
        binding.swipeToRefresh.setOnRefreshListener{
            viewModel.refreshHistoryList()
        }
        return binding.root
    }

    override fun onStart() {
        viewModel.displayFavouriteHistoryList()
        super.onStart()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}