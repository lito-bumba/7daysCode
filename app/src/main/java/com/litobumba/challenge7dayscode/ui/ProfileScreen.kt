package com.litobumba.challenge7dayscode.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.litobumba.challenge7dayscode.AppViewModel
import com.litobumba.challenge7dayscode.R
import com.litobumba.challenge7dayscode.ui.theme.ColorPrimary
import com.litobumba.challenge7dayscode.webclient.DtoRepos

@Preview(showBackground = true)
@Composable
fun ShowProfileScreen() {
    ProfileScreen(
        viewModel = viewModel(),
        navController = rememberNavController(),
        state = ProfileUiState(
            userName = "lito-bumba",
            image = "https://avatars.githubusercontent.com/u/90806272?v=4",
            name = "Lito Bumba",
            bio = "Android Developer",
            repos = listOf(
                DtoRepos("Repositório Teste 1", "Testando Repositório"),
                DtoRepos("Repositório Teste 2", "Testando Repositório"),
                DtoRepos("Repositório Teste 3", "Testando Repositório")
            )
        )
    )
}

@Composable
fun TheAppBar(
    scrollUpState: State<Boolean?>,
    navController: NavController
) {
    val position by animateFloatAsState(
        if (scrollUpState.value == true)
            -150F
        else
            0F
    )

    Surface(
        modifier = Modifier.graphicsLayer { translationY = position },
        color = ColorPrimary
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = { navController.navigate("firstScreen") }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back To FirstScreen",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun ProfileScreen(
    viewModel: AppViewModel,
    navController: NavController,
    state: ProfileUiState
) {

    val scrollState = rememberLazyListState()
    val scrollUpState = viewModel.scrollUp.observeAsState()

    viewModel.updateScrollPosition(scrollState.firstVisibleItemIndex)

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 40.dp),
            state = scrollState
        ) {
            item {
                ProfileHeader(navController, state)
            }

            item {
                if (state.repos.isNotEmpty()) {
                    Text(
                        text = stringResource(R.string.repositories),
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

        TheAppBar(scrollUpState, navController)
    }
}

@Composable
fun ProfileHeader(navController: NavController, state: ProfileUiState) {
    Box {
        Box(
            Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .background(ColorPrimary)
                .height(120.dp)
        )
        AsyncImage(
            model = state.image,
            contentDescription = "profile-picture",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(150.dp)
                .width(150.dp)
                .offset(x = 0.dp, y = 35.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )
    }
    Spacer(modifier = Modifier.size(50.dp))

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