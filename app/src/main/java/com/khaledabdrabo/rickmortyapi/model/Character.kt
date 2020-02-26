package com.khaledabdrabo.rickmortyapi.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Character(
    @field:PrimaryKey val id: Int = 0,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    @Embedded(prefix = "orig_") val origin : Origin,
    @Embedded(prefix = "loc_") val location: Origin,
    val image: String,
    val url: String,
    val created: String): Serializable

data class Origin(
    val name: String,
    val url: String): Serializable