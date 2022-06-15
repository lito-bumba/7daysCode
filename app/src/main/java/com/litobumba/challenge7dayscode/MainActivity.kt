package com.litobumba.challenge7dayscode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.litobumba.challenge7dayscode.ui.ProfileScreen

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            ProfileScreen(user = "lito-bumba")
        }
    }
}