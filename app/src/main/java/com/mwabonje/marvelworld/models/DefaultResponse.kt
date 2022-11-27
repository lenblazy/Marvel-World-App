package com.mwabonje.marvelworld.models

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DefaultResponse(
    @SerializedName("code")
    var code: Int,

    @SerializedName("status")
    var status: String,

    @SerializedName("data")
    var data: MainData?,
) : Parcelable

@Parcelize
data class MainData(

    @SerializedName("results")
    var marvelCharacters: ArrayList<MarvelCharacter>,

    ) : Parcelable

@Parcelize
data class MarvelCharacter(

    @SerializedName("id")
    var characterId: Long,

    @SerializedName("name")
    var characterName: String,

    @SerializedName("description")
    var characterDescription: String,

    @SerializedName("comics")
    var comics: Comic?,

    @SerializedName("series")
    var series: Serie?,

    @SerializedName("stories")
    var stories: Story?,

    ) : Parcelable

@Parcelize
data class Comic(

    @SerializedName("items")
    var comics: ArrayList<MyItem>?,

    ) : Parcelable

@Parcelize
data class Serie(

    @SerializedName("items")
    var series: ArrayList<MyItem>?,

    ) : Parcelable

@Parcelize
data class Story(

    @SerializedName("items")
    var stories: ArrayList<MyItem>?,

) : Parcelable

@Parcelize
data class MyItem(

    @SerializedName("name")
    var name: String,

) : Parcelable



