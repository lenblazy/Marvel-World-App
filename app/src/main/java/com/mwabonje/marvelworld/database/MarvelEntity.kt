package com.mwabonje.marvelworld.database

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tbl_marvel_character")
class MarvelEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id") var id: Long,

    @ColumnInfo(name = "name")
    var characterName: String,

    @ColumnInfo(name = "description")
    var characterDescription: String,
): Parcelable