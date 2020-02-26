package com.khaledabdrabo.rickmortyapi.network

import io.reactivex.Observable
import com.khaledabdrabo.rickmortyapi.model.Character
import retrofit2.http.GET
import retrofit2.http.Url

interface CharacterApi {
    @GET
    fun getCharacters(@Url url: String?): Observable<List<Character>>
}