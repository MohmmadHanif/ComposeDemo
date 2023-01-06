package com.example.composedemo.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {

    val navigation: NavHostController = rememberNavController()

    NavHost(navController = navigation, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            NavigationHomeScreen(navigation)
        }

        composable(NavigationItem.Detail.route) {
            val name = it.arguments?.getString("name")
            DetailScreen(name = name)
        }
    }
}

@Composable
fun NavigationHomeScreen(navigation: NavHostController) {
    var text by rememberSaveable() {
        mutableStateOf("")
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text(text = "Enter Your Name") },
                leadingIcon = {
                    Image(
                        Icons.Filled.Person2,
                        contentDescription = null
                    )
                })

            Button(onClick = { navigation.navigate("detail/$text") }, enabled = text.isNotEmpty()) {

                Text(
                    text = "Create BirthDay Card",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Composable
fun DetailScreen(name: String?) {
    Surface() {
        Card(
            Modifier
                .padding(20.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    Icons.Filled.Cake,
                    modifier = Modifier.size(100.dp),
                    contentDescription = null
                )
                Text(text = "Happy Birth-Day " + name.toString() + "🥳")
            }
        }
    }
}