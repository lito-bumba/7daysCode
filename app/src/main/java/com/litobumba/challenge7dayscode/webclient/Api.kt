package com.litobumba.challenge7dayscode.webclient

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("users/{user}")
    suspend fun getUser(@Path("user") user: String): Dto

    @GET("users/{user}/repos")
    suspend fun getUserRepos(@Path("user") user: String): List<DtoRepos>

}