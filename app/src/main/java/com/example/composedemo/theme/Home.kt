package com.example.composedemo.theme

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.composedemo.main.ui.theme.JetnewsTheme

@Composable
fun Home() {
    Text(text = "hanif")
}

@Preview("Featured Post", showBackground = true)
@Composable
fun HomePreview() {
    JetnewsTheme {

        Home()
    }
}