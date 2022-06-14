package com.litobumba.challenge7dayscode.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.litobumba.challenge7dayscode.ProfileUiState
import com.litobumba.challenge7dayscode.webclient.Dto

@Composable
fun ProfileDetail(state: ProfileUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Box {
            Box(
                Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                    .background(Color.DarkGray)
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
                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error)
                    CircularProgressIndicator(
                        color = Color.Gray, strokeWidth = 20.dp,
                        modifier = Modifier.background(Color.White)
                    )
                else SubcomposeAsyncImageContent()
            }
        }

        Spacer(modifier = Modifier.size(75.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = state.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = state.userName,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = state.bio,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Repository",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.height(10.dp))

            Repository(
                "estudos",
                "Repositorio apenas para Estudos realizado"
            )

            Repository(
                "repositorio 1",
                "Repositorio apenas para teste"
            )

            Repository(
                "aboutme",
                "Site sobre mim feito para imersão Hipers CSS"
            )

            Repository(
                "repositorio 1",
                "Repositorio apenas para teste"
            )

            Repository(
                "aboutme",
                "Site sobre mim feito para imersão Hipers CSS"
            )

            Repository(
                "repositorio 1",
                "Repositorio apenas para teste"
            )

            Repository(
                "repositorio 4",
                "Repositorio apenas para teste 4"
            )

        }
    }
}

@Composable
fun Repository(title: String, description: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(34.dp)
            .clip(RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp))
            .background(Color.DarkGray)
            .padding(9.dp, 4.dp),
        content = {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }
    )
    Text(
        text = description,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        modifier = Modifier.padding(horizontal = 10.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
}