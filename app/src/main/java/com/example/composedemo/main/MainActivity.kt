package com.example.composedemo.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.RecyclerViewComposeActivity
import com.example.composedemo.bottomnavigation.BottomNavigationActivity
import com.example.composedemo.cryptocurrencydemo.presentation.CryptoMainActivity
import com.example.composedemo.layoutCodeLabActivity
import com.example.composedemo.main.ui.theme.ComposeDemoTheme
import com.example.composedemo.navigation.NavigationMainActivity
import com.example.composedemo.state.StateMainActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Greeting()
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Greeting() {
    val context = LocalContext.current
    Column(Modifier.fillMaxSize()) {

        //RecyclerView Demo
        ElevatedButton(
            onClick = {
                context.startActivity(
                    Intent(
                        context,
                        RecyclerViewComposeActivity::class.java
                    )
                )
            },
            modifier = Modifier
                .padding(40.dp, 20.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "RecyclerViewDemo")
        }


        //layout

        ElevatedButton(
            onClick = {
                context.startActivity(
                    Intent(
                        context,
                        layoutCodeLabActivity::class.java
                    )
                )
            },
            modifier = Modifier
                .padding(40.dp, 20.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "layoutCodeLab")
        }

        //state

        ElevatedButton(
            onClick = {
                context.startActivity(
                    Intent(
                        context,
                        StateMainActivity::class.java
                    )
                )
            },
            modifier = Modifier
                .padding(40.dp, 20.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "StateCodeLab")
        }


        //Navigation

        ElevatedButton(
            onClick = {
                context.startActivity(
                    Intent(
                        context,
                        NavigationMainActivity::class.java
                    )
                )
            },
            modifier = Modifier
                .padding(40.dp, 20.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Navigation")
        }


        //Bottom Navigation

        ElevatedButton(
            onClick = {
                context.startActivity(
                    Intent(
                        context,
                        BottomNavigationActivity::class.java
                    )
                )
            },
            modifier = Modifier
                .padding(40.dp, 20.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Bottom Navigation")
        }


        //Bottom Navigation

        ElevatedButton(
            onClick = {
                context.startActivity(
                    Intent(
                        context,
                        CryptoMainActivity::class.java
                    )
                )
            },
            modifier = Modifier
                .padding(40.dp, 20.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Crypto")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDemoTheme {
        Greeting()
    }
}