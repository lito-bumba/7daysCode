package com.litobumba.challenge7dayscode.webclient

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("users/{NOME_DO_USUARIO}")
    suspend fun pegarUsuario(@Path("NOME_DO_USUARIO") NOME_DO_USUARIO: String): Dto

}