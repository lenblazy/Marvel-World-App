package com.mwabonje.marvelworld.models

enum class DetailType {
    Comic,
    Series,
    Story
}

data class Details(
    val name: String,
    val type: DetailType
)