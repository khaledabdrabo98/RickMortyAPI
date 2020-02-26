package com.khaledabdrabo.rickmortyapi.ui.character

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.khaledabdrabo.rickmortyapi.base.BaseViewModel
import com.khaledabdrabo.rickmortyapi.model.Character

class CharacterViewModel:BaseViewModel() {
    private val characterImage = MutableLiveData<String>()
    private val characterName = MutableLiveData<String>()
    private val characterInfo = MutableLiveData<String>()

    fun bind(character: Character){
        characterImage.value = character.image
        characterName.value = character.name
        characterInfo.value = "id: ${character.id} - created in ${TextUtils.substring(character.created,0, 4)}"
    }

    fun getCharacterImage():MutableLiveData<String>{
        return characterImage
    }

    fun getCharacterName():MutableLiveData<String>{
        return characterName
    }

    fun getCharacterInfo():MutableLiveData<String>{
        return characterInfo
    }
}