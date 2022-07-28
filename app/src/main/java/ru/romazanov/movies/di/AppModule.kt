package ru.romazanov.movies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.romazanov.movies.data.reposotiry.Repository
import ru.romazanov.movies.data.retrofit.RetrofitApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val baseUrl = "https://api.nytimes.com/svc/movies/v2/reviews/"

    @Provides
    @Singleton
    fun provideRetrofit(): RetrofitApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)

    }

    @Provides
    @Singleton
    fun provideRepository(retrofit: RetrofitApi): Repository {
        return Repository(retrofit)
    }
}


