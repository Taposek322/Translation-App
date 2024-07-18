package com.taposek322.translationapp.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.taposek322.translationapp.presentation.ui.translation.TranslationViewModel
import com.taposek322.translationapp.presentation.ui.translationhistory.TranslationHistoryViewModel
import com.taposek322.translationapp.presentation.util.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(TranslationViewModel::class)
    abstract fun bindTranslationViewModel(translationViewModel: TranslationViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TranslationHistoryViewModel::class)
    abstract fun bindTranslationHistoryViewModel(translationHistoryViewModel: TranslationHistoryViewModel):ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory):ViewModelProvider.Factory
}