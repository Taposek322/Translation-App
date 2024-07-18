package com.taposek322.translationapp.di

import android.app.Application
import com.taposek322.translationapp.di.db.DataBaseModule
import com.taposek322.translationapp.di.remote.TranslatorApiModule
import com.taposek322.translationapp.di.viewmodel.ViewModelsModule
import com.taposek322.translationapp.presentation.ui.translation.TranslationFragment
import com.taposek322.translationapp.presentation.ui.translationhistory.TranslationHistoryFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, TranslatorApiModule::class, DataBaseModule::class, ViewModelsModule::class])
interface AppComponent{

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance application: Application):AppComponent
    }
    
    fun injectToTranslationFragment(translationFragment: TranslationFragment)

    fun injectToTranslationHistoryFragment(translationHistoryFragment: TranslationHistoryFragment)
}