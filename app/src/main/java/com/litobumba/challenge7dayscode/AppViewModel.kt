package com.litobumba.challenge7dayscode

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.litobumba.challenge7dayscode.data.Repository
import com.litobumba.challenge7dayscode.ui.ProfileState
import com.litobumba.challenge7dayscode.ui.ProfileUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException

class AppViewModel(private val repository: Repository = Repository()): ViewModel() {

    private var _profile = MutableLiveData(ProfileUiState())
    val profile: LiveData<ProfileUiState> = _profile

    private val _scrollUp = MutableLiveData(false)
    val scrollUp: LiveData<Boolean> = _scrollUp

    private var lastScrollIndex = 0

    fun getUser(context: Context, user: String): Flow<ProfileState> = flow {
        try {
            val user = repository.getUser(user)
            delay(700L)
            _profile.value = user
            emit(ProfileState(profile = user))
        } catch (e: HttpException) {
            delay(700L)
            emit(ProfileState(error = context.getString(R.string.userNotFound)))
        } catch (e: IOException) {
            delay(700L)
            emit(ProfileState(error = context.getString(R.string.internetConnection)))
        } catch (e: Exception) {
            delay(700L)
            emit(ProfileState(error = context.getString(R.string.parameterError)))
        }
    }

    fun updateScrollPosition(newScrollIndex: Int) {
        if (newScrollIndex == lastScrollIndex) return

        _scrollUp.value = newScrollIndex > lastScrollIndex
        lastScrollIndex = newScrollIndex
    }

}