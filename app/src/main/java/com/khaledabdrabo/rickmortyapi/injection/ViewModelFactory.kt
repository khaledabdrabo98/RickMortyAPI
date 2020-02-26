package com.khaledabdrabo.rickmortyapi.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.khaledabdrabo.rickmortyapi.model.database.AppDatabase
import com.khaledabdrabo.rickmortyapi.ui.character.CharacterListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "characters").build()
            @Suppress("UNCHECKED_CAST")
            return CharacterListViewModel(db.characterDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}