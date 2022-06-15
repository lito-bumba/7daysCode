package com.litobumba.challenge7dayscode

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.litobumba.challenge7dayscode.ui.ProfileUiState
import com.litobumba.challenge7dayscode.webclient.*

class Repository(private val api: Api = Retrofit().api()) {

    var uiState by mutableStateOf(ProfileUiState())
        private set

    suspend fun getUser(user: String) {
        try {
            val profile = api.getUser(user).DtoToUiState()
            val repos = api.getUserRepos(user).map {
                it.dtoToReposList()
            }
            uiState = profile.copy(repos = repos)
        } catch (e: Exception) {
            Log.e("Error to find user: ", e.message!!)
        }
    }

}