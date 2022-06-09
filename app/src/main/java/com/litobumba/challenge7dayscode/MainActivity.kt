package com.litobumba.challenge7dayscode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.litobumba.challenge7dayscode.ui.theme.Challenge7daysCodeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Challenge7daysCodeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    profileScreen()
                }
            }
        }
    }
}

@Composable
fun profileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .background(Color.DarkGray)
        )

        Image(
            painterResource(id = R.drawable.image_perfil),
            contentDescription = "profile-picture",
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .clip(CircleShape)
                .align(CenterHorizontally)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Lito Bumba",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(CenterHorizontally)
            )
            Text(
                text = "lito-bumba",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Android Developer | Kotlin Language",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Repository",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.height(10.dp))

            repository(
                "estudos",
                "Repositorio apenas para Estudos realizado"
            )

            repository(
                "repositorio 1",
                "Repositorio apenas para teste"
            )

            repository(
                "aboutme",
                "Site sobre mim feito para imersão Hipers CSS"
            )

            repository(
                "repositorio 1",
                "Repositorio apenas para teste"
            )

            repository(
                "aboutme",
                "Site sobre mim feito para imersão Hipers CSS"
            )

            repository(
                "repositorio 1",
                "Repositorio apenas para teste"
            )

            repository(
                "repositorio 4",
                "Repositorio apenas para teste 4"
            )

        }
    }
}

@Composable
fun repository(title: String, description: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(34.dp)
            .clip(RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp))
            .background(Color.Gray)
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

@Preview(showBackground = true)
@Composable
fun showProfileScreen() {
    profileScreen()
}