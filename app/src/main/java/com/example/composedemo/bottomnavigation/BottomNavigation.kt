package com.example.composedemo.bottomnavigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationDemo(nav: NavController) {

    val list = listOf(BottomNavItem.Home, BottomNavItem.Feed, BottomNavItem.Setting)

    BottomNavigation {

        val backStackEntry by nav.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        list.forEach {
            BottomNavigationItem(
                selected = currentRoute == it.route,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(alpha = 0.4f),
                icon = { Icon(painter = painterResource(id = it.icon), contentDescription = "") },
                label = { Text(text = it.name) },
                onClick = { nav.navigate(it.route) }
            )
        }
    }
}