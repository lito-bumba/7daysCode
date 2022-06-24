package com.litobumba.challenge7dayscode.ui

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.litobumba.challenge7dayscode.UseCase
import com.litobumba.challenge7dayscode.ui.theme.ColorPrimary
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FirstScreen(viewModel: UseCase, navController: NavController) {

    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)

    BackdropScaffold(
        appBar = { },
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    backgroundColor = Color.White,
                    contentColor = Color.Red,
                    snackbarData = data
                )
            }
        },
        backLayerContent = { BackLayer() },
        backLayerBackgroundColor = Color.White,
        peekHeight = 80.dp,
        frontLayerContent = {
            FrontLayer(
                viewModel = viewModel,
                navController = navController,
                snackbarHostState = scaffoldState.snackbarHostState
            )
        },
        frontLayerBackgroundColor = ColorPrimary
    )
}

@Composable
private fun BackLayer() {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "DevHub",
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Github Profile Finder",
            fontSize = 20.sp,
            color = ColorPrimary
        )
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "by:")
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = "Lito Bumba", fontSize = 40.sp,
                style = TextStyle(
                    textDecoration = TextDecoration.Underline,
                    letterSpacing = 5.sp
                ),
                modifier = Modifier.clickable {
                    val webIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/in/lito-bumba/")
                    )
                    context.startActivity(webIntent)
                }
            )
        }
    }
}

@Composable
private fun FrontLayer(
    viewModel: UseCase,
    navController: NavController,
    snackbarHostState: SnackbarHostState
) {
    val context = LocalContext.current

    val scope = rememberCoroutineScope()
    var userName by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 30.dp)
    ) {

        Text(
            text = "DevHub",
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Let's find a Github Profile",
            fontSize = 20.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(100.dp))

        OutlinedTextField(
            value = userName,
            onValueChange = {
                userName = it
            },
            singleLine = true,
            label = { Text("User Name", fontSize = 18.sp) },
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 20.sp
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                cursorColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                backgroundColor = Color.Transparent,
                unfocusedBorderColor = Color.White,
                focusedBorderColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(.9F)
        )

        Spacer(modifier = Modifier.height(60.dp))


        var loading by remember { mutableStateOf(false) }

        LoadingButton(
            onClick = {
                if (userName.isBlank()) {
                    return@LoadingButton
                }

                loading = true

                scope.launch {
                    viewModel.getUser(userName).collect { state ->

                        loading = false

                        if (!state.error.isNullOrEmpty()) {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = state.error!!,
                                    duration = SnackbarDuration.Short
                                )
                            }
                            return@collect
                        }

                        /*Toast.makeText(
                            context,
                            state.profile.toString(),
                            Toast.LENGTH_SHORT
                        ).show()*/
                        navController.navigate("profileScreen")
                    }
                }


            },
            modifier = Modifier
                .padding(all = 16.dp)
                .height(50.dp)
                .width(130.dp),
            loading = loading,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = ColorPrimary
            )
        ) {
            Text(text = "Enter", fontSize = 18.sp)
        }

    }
}