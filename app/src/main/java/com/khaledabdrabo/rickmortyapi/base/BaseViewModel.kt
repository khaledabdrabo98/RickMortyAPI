package com.khaledabdrabo.rickmortyapi.base

import androidx.lifecycle.ViewModel
import com.khaledabdrabo.rickmortyapi.injection.component.DaggerViewModelInjector
import com.khaledabdrabo.rickmortyapi.injection.component.ViewModelInjector
import com.khaledabdrabo.rickmortyapi.injection.module.NetworkModule
import com.khaledabdrabo.rickmortyapi.ui.character.CharacterListViewModel
import com.khaledabdrabo.rickmortyapi.ui.character.CharacterViewModel

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is CharacterListViewModel -> injector.inject(this)
            is CharacterViewModel -> injector.inject(this)
        }
    }
}