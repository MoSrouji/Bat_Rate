package com.example.myapplication.auth.domain.entities

data class User(
    val userId: String?=null,
    val fullName: String?=null,
    val email: String?=null,
    val bio : String?=null,
    val saveToWatchLater: List<Int> = emptyList<Int>(),
    val saveToWatched: List<Int> = emptyList<Int>(),
    val searchHistory: MutableList<String > ? =mutableListOf(),
)
