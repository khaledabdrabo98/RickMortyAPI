package com.khaledabdrabo.rickmortyapi.ui.character

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.khaledabdrabo.rickmortyapi.R
import com.khaledabdrabo.rickmortyapi.base.BaseViewModel
import com.khaledabdrabo.rickmortyapi.model.Character
import com.khaledabdrabo.rickmortyapi.model.CharacterDao
import com.khaledabdrabo.rickmortyapi.network.CharacterApi
import javax.inject.Inject


class CharacterListViewModel(private val characterDao: CharacterDao):BaseViewModel(){
    @Inject
    lateinit var characterApi: CharacterApi
    val characterListAdapter: CharacterListAdapter = CharacterListAdapter()

    private val url: String = generateRandomCharacters()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadCharacters(url) }

    private lateinit var subscription: Disposable

    init{
        loadCharacters(url)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadCharacters(url: String?){
        subscription = Observable.fromCallable { characterDao.all }
            .concatMap {
                    dbCharacterList ->
                if(dbCharacterList.isEmpty())
                    characterApi.getCharacters(url).concatMap {
                            apiCharacterList -> characterDao.insertAll(*apiCharacterList.toTypedArray())
                        Observable.just(apiCharacterList)
                    }
                else
                    Observable.just(dbCharacterList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { onRetrievePostListError() }
            )
    }

    fun forceLoadCharacters(url: String?){
        Observable.fromCallable{characterDao.deleteAll()}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { (loadCharacters(url)) }
            .subscribe()
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(characterList:List<Character>){
        characterListAdapter.updateCharacterList(characterList)
    }

    private fun onRetrievePostListError(){
        errorMessage.value = R.string.error_occurred
    }

    fun generateRandomCharacters() : String{
        var apiUrl: String = "api/character/"
        for (i in 1..20) {
            apiUrl = if(i == 20)
                ("$apiUrl${i * (1..20).random()}")
            else
                ("$apiUrl${i * (1..20).random()},")
        }
        return apiUrl
    }
}