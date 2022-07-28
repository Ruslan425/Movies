package ru.romazanov.movies.data.reposotiry


import ru.romazanov.movies.data.models.Answer
import ru.romazanov.movies.data.retrofit.RetrofitApi

class Repository(
    private val retrofit: RetrofitApi
) {

    private val apiKey: Pair<String, String> = Pair("api-key", "vdYos1OGpcJePo8b5qQbcSZCfkdfs4GG")
    private val offset: Pair<String, String> = Pair("offset", "1")
    private var map: MutableMap<String, String> = mutableMapOf(apiKey, offset)

    suspend fun doNetworkCall(offset: String): Answer {
        return if (offset.isEmpty()) {
            retrofit.getMovies(map)
        } else {
            map.replace("offset", offset)
            retrofit.getMovies(map)
        }
    }

}