package ru.romazanov.movies.data.models

data class Answer(
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)