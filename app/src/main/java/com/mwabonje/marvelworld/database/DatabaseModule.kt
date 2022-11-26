package com.mwabonje.marvelworld.database

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    private var dbInstance: MarvelDatabase? = null

    @Provides
    fun getRoomDbInstance(context: Application): MarvelDatabase {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(context,
                MarvelDatabase::class.java, "marvel_db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }

        return dbInstance!!
    }

    @Provides
    fun marvelDao(appDb: MarvelDatabase) = appDb.marvelDao()

}