package com.nagy.movieapp.common.data.api

import com.nagy.movieapp.common.data.api.model.ApiMovieDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("${ApiConstants.MOVIE_ENDPOINT}{id}")
    suspend fun getMovieDetails(@Path("id") id : Long) : ApiMovieDetails
}