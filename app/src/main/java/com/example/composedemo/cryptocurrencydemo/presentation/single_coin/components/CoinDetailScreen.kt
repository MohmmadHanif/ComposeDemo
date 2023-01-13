package com.example.composedemo.cryptocurrencydemo.presentation.single_coin.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composedemo.cryptocurrencydemo.presentation.single_coin.CoinDetailViewModel
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CoinDetailScreen(viewModel: CoinDetailViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {

        state.coin?.let {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(), contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${it.rank}. ${it.name} (${it.symbol})",
                            style = MaterialTheme.typography.h2,
                            modifier = Modifier.weight(8f)
                        )

                        androidx.compose.material.Text(
                            text = if (it.isActive) "Active" else "inactive",
                            color = if (it.isActive) Color.Gray else Color.Red,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(2f)
                        )
                    }

                    Spacer(modifier = Modifier.padding(15.dp))

                    Text(text = it.description, style = MaterialTheme.typography.body2)

                    Spacer(modifier = Modifier.padding(15.dp))

                    Text(text = "Tag", style = MaterialTheme.typography.h3)

                    Spacer(modifier = Modifier.padding(15.dp))

                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        it.tags.forEach { tag ->
                            CoinTag(tag = tag)
                        }
                    }

                    Spacer(modifier = Modifier.padding(15.dp))

                    Text(text = "Team Members", style = MaterialTheme.typography.h3)

                    Spacer(modifier = Modifier.padding(15.dp))
                }

                items(it.team) { team ->
                    TeamListItem(
                        teamMember = team, modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider()
                }
            }
        }

        if (state.error.isNotEmpty()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }


        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}