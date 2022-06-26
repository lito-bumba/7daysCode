package com.litobumba.challenge7dayscode.data.api

import com.litobumba.challenge7dayscode.ui.ProfileUiState

data class Dto(
    val avatar_url: String,
    val bio: String,
    val login: String,
    val name: String
)

fun Dto.dtoToUiState(): ProfileUiState {
    return ProfileUiState(
        userName = login,
        image = avatar_url,
        name = name,
        bio = bio,
    )
}

data class DtoRepos(
    val name: String = "",
    val description: String? = null
)

fun DtoRepos.dtoToReposList() = DtoRepos(
    name = name,
    description = description ?: ""
)