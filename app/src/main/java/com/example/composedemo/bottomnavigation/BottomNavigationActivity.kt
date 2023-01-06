package com.example.composedemo.bottomnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composedemo.bottomnavigation.ui.theme.ComposeDemoTheme

class BottomNavigationActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {

                val nav = rememberNavController()

                Scaffold(bottomBar = { BottomNavigationDemo(nav = nav) }) {
                    val p = it
                    NavHost(navController = nav, startDestination = "home") {
                        composable("home") {
                            Greeting2(name = "Home Screen")
                        }
                        composable("feed") {
                            Greeting2(name = "Feed Screen")
                        }
                        composable("setting") {
                            Greeting2(name = "Setting Screen")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = name)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview7() {
    ComposeDemoTheme {
        Greeting2("Android")
    }
}