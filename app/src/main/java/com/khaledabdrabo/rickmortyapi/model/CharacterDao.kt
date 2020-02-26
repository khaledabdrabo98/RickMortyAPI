package com.khaledabdrabo.rickmortyapi.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CharacterDao {
    @get:Query("SELECT * FROM character")
    val all: List<Character>

    @Insert
    fun insertAll(vararg character: Character)

    @Query("DELETE FROM character")
    fun deleteAll()
}