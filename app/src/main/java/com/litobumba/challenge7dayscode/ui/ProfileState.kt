package com.litobumba.challenge7dayscode.ui

import com.litobumba.challenge7dayscode.data.api.DtoRepos

data class ProfileUiState(
    val userName: String = "",
    val image: String = "",
    val name: String = "",
    val bio: String = "",
    var repos: List<DtoRepos> = emptyList()
)

data class ProfileState(
    var profile: ProfileUiState? = null,
    var error: String? = null
)