package com.mwabonje.marvelworld.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MarvelDao {

    @Query("SELECT * FROM tbl_marvel_character")
    fun getMarvelCharacters(): LiveData<List<MarvelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<MarvelEntity>)

}