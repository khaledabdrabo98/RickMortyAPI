package com.khaledabdrabo.rickmortyapi.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.khaledabdrabo.rickmortyapi.model.Character
import com.khaledabdrabo.rickmortyapi.model.CharacterDao

@Database(entities = [Character::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}