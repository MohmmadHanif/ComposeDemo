package com.example.composedemo.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ItemCard(modifier: Modifier = Modifier, name: String) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(), elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Icon(
                Icons.Filled.Assignment,
                contentDescription = null,
                modifier = modifier.size(44.dp)
            )
            Surface(
                modifier = modifier
                    .align(CenterVertically)
            ) {
                Text(
                    text = name
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemCardPreview() {
    Surface() {
    }
}