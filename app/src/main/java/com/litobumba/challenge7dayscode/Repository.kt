package com.litobumba.challenge7dayscode

import com.litobumba.challenge7dayscode.ui.ProfileUiState
import com.litobumba.challenge7dayscode.webclient.*

class Repository(private val api: Api = Retrofit().api()) {

    suspend fun getUser(user: String): ProfileUiState {
        val profile = api.getUser(user).DtoToUiState()
        val repos = api.getUserRepos(user).map {
            it.dtoToReposList()
        }
        return profile.copy(repos = repos)
    }

}