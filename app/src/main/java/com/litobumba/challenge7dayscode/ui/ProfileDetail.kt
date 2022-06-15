package com.litobumba.challenge7dayscode.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
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
import com.litobumba.challenge7dayscode.Repository
import com.litobumba.challenge7dayscode.ui.theme.ColorPrimary
import com.litobumba.challenge7dayscode.webclient.DtoRepos

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
    val userName: String = "",
    val image: String = "",
    val name: String = "",
    val bio: String = "",
    val repos: List<DtoRepos> = emptyList()
)

@Composable
fun ProfileScreen(user: String, repository: Repository = Repository()) {

    val uiState = repository.uiState

    LaunchedEffect(null) {
        repository.getUser(user)
    }

    ProfileDetail(uiState)
}

@Composable
fun ProfileDetail(state: ProfileUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            item {
                ProfileHeader(state = state)
            }
            item {
                if (state.repos.isNotEmpty()) {
                    Text(
                        text = "Repositories",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth()
                    )
                }
            }

            items(state.repos) { repo ->
                RepositoryItem(repo)
            }
        }
    }
}

@Composable
fun ProfileHeader(state: ProfileUiState) {
    Box {
        Box(
            Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .background(ColorPrimary)
                .height(150.dp)
        )
        SubcomposeAsyncImage(
            model = state.image,
            contentDescription = "profile-picture",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(150.dp)
                .width(150.dp)
                .offset(x = 0.dp, y = 75.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                Spacer(modifier = Modifier.height(10.dp))
                CircularProgressIndicator(
                    color = Color.Gray, strokeWidth = 15.dp,
                    modifier = Modifier
                        .background(Color.White)
                )
                Spacer(modifier = Modifier.height(10.dp))
            } else SubcomposeAsyncImageContent()
        }
    }
    Spacer(modifier = Modifier.size(75.dp))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = state.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = state.userName,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = state.bio,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(15.dp))
    }
}

@Composable
fun RepositoryItem(repo: DtoRepos) {

    Card(
        modifier = Modifier.padding(8.dp),
        elevation = 4.dp
    ) {
        Column {
            Text(
                text = repo.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2d333b))
                    .padding(8.dp),
                fontSize = 24.sp,
                color = Color.White
            )
            if (repo.description?.isNotBlank() == true) {
                Text(
                    text = repo.description,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}