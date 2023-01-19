package com.example.composedemo.cryptocurrencydemo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composedemo.cryptocurrencydemo.presentation.coin_list.components.CoinListScreen
import com.example.composedemo.cryptocurrencydemo.presentation.single_coin.components.CoinDetailScreen
import com.example.composedemo.cryptocurrencydemo.presentation.ui.theme.ComposeDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme{
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.CoinScreenListScreen.route
                ) {
                    composable(Screen.CoinScreenListScreen.route) {
                        CoinListScreen(navigation = navController)
                    }

                    composable(Screen.CoinDetailScreen.route + "/{coinId}") {
                        CoinDetailScreen()
                    }
                }
            }
        }
    }
}
