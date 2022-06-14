package com.litobumba.challenge7dayscode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.litobumba.challenge7dayscode.ui.ProfileDetail
import com.litobumba.challenge7dayscode.ui.theme.Challenge7daysCodeTheme
import com.litobumba.challenge7dayscode.webclient.Dto

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Challenge7daysCodeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ProfileScreen("lito-bumba")
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(userName: String, repository: Repository = Repository()) {

    val userFound by repository.pegarUsuario(userName).collectAsState(initial = null)

    userFound?.let {

        ProfileDetail(
            state = ProfileUiState(
                userName = it.login,
                name = it.name,
                bio = it.bio,
                image = it.avatar_url
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ShowProfileScreen() {
    ProfileDetail(
        state = ProfileUiState(
            userName = "lito-bumba",
            image = "https://avatars.githubusercontent.com/u/90806272?v=4",
            name = "Lito Bumba",
            bio = "Android Developer"
        )
    )
}

data class ProfileUiState(
    val userName: String,
    val image: String,
    val name: String,
    val bio: String
)