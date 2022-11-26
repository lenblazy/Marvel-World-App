package com.mwabonje.marvelworld.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MarvelEntity::class], version = 2, exportSchema = false)
abstract class MarvelDatabase: RoomDatabase() {

    abstract fun marvelDao() : MarvelDao

}