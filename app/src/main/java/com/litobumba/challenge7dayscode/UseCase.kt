package com.litobumba.challenge7dayscode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.litobumba.challenge7dayscode.ui.ProfileState
import com.litobumba.challenge7dayscode.ui.ProfileUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException

class UseCase(private val repository: Repository = Repository()): ViewModel() {

    private var _profile = MutableLiveData(ProfileUiState())
    val profile: LiveData<ProfileUiState> = _profile

    fun getUser(user: String): Flow<ProfileState> = flow {
        try {
            val user = repository.getUser(user)
            delay(700L)
            _profile.value = user
            emit(ProfileState(profile = user))
        } catch (e: HttpException) {
            delay(700L)
            emit(ProfileState(error = "User Not Found"))
        } catch (e: IOException) {
            delay(700L)
            emit(ProfileState(error = "Check your Internet Connection"))
        } catch (e: Exception) {
            delay(700L)
            emit(ProfileState(error = "Parameter Error"))
        }
    }

}