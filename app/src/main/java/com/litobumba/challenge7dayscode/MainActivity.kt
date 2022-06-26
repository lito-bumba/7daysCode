package com.litobumba.challenge7dayscode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.litobumba.challenge7dayscode.ui.FirstScreen
import com.litobumba.challenge7dayscode.ui.ProfileScreen
import com.litobumba.challenge7dayscode.ui.ProfileUiState

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<AppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val profile: ProfileUiState by viewModel.profile.observeAsState(ProfileUiState())

            NavHost(navController = navController, startDestination = "firstScreen") {

                composable(route = "firstScreen") {
                    FirstScreen(viewModel, navController)
                }

                composable(route = "profileScreen") {
                    ProfileScreen(viewModel, navController, profile)
                }
            }

        }
    }
}