package com.example.retrofitcards

data class CardDeckModel(
    val cards: List<Card>,
    val deck_id: String,
    val remaining: Int,
    val success: Boolean
)