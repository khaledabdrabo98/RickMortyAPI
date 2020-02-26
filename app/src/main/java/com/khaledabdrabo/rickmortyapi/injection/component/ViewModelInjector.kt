package com.khaledabdrabo.rickmortyapi.injection.component

import dagger.Component
import com.khaledabdrabo.rickmortyapi.injection.module.NetworkModule
import com.khaledabdrabo.rickmortyapi.ui.character.CharacterListViewModel
import com.khaledabdrabo.rickmortyapi.ui.character.CharacterViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(characterListViewModel: CharacterListViewModel)

    fun inject(characterViewModel: CharacterViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}