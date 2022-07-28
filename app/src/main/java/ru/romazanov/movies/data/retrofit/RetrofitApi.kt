package ru.romazanov.movies.data.retrofit

import retrofit2.http.GET
import retrofit2.http.QueryMap
import ru.romazanov.movies.data.models.Answer


interface RetrofitApi {

    @GET("all.json")
    suspend fun getMovies(
        @QueryMap() options: Map<String, String>
    ): Answer

}