package com.litobumba.challenge7dayscode

import android.util.Log
import com.litobumba.challenge7dayscode.webclient.Api
import com.litobumba.challenge7dayscode.webclient.Dto
import com.litobumba.challenge7dayscode.webclient.Retrofit
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class Repository(private val api: Api = Retrofit().api()) {

    fun pegarUsuario(nomeUsuario: String) = flow {
        try {
            val usuario = api.pegarUsuario(nomeUsuario)
            emit(usuario)
        } catch (e: Exception) {
            Log.e("Erro", e.message!!)
        }
    }

}