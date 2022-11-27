package com.mwabonje.marvelworld.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MarvelDao {

    @Query("SELECT * FROM tbl_marvel_character")
    suspend fun getMarvelCharacters(): List<MarvelEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<MarvelEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(user: MarvelEntity): Long

}