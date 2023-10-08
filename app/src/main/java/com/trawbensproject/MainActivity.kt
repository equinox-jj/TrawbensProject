package com.trawbensproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.trawbensproject.navigation.SetupNavGraph
import com.trawbensproject.ui.theme.TrawbensProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrawbensProjectTheme {
                val navController = rememberNavController()

                SetupNavGraph(
                    navController = navController,
                    startDestination = "home_screen",
                )
            }
        }
    }
}