@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.composedemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.main.ui.theme.ComposeDemoTheme
import java.util.*

class layoutCodeLabActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            ComposeDemoTheme {
                HomeScreen()
            }
        }
    }
}

val alineYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga,
).map { AlineYourBodyDataModel(it.first, it.second) }

data class AlineYourBodyDataModel(val drawable: Int, val string: Int)

val favoriteDataCollection = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down,
).map { FavoriteDataCollectionModel(it.first, it.second) }

data class FavoriteDataCollectionModel(val drawable: Int, val string: Int)


@Composable
fun HomeScreen() {
    Scaffold(bottomBar = { HomeBottomNavigation() }) { padding  ->
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()) {
            Spacer(modifier = Modifier.padding(16.dp))
            SearchBar(Modifier.padding(16.dp))
            HomeSection(title = R.string.align_your_body) {
                AlineYourBodyRow()
            }
            HomeSection(title = R.string.favorite_collections) {
                FavoriteCollectionsGrid()
            }
            Spacer(modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun HomeBottomNavigation() {
    val context = LocalContext.current
    BottomNavigation(backgroundColor = MaterialTheme.colorScheme.background) {
        BottomNavigationItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Spa,
                    contentDescription = null
                )
            },
            label = { Text(text = "Spa") })
        BottomNavigationItem(
            selected = false,
            onClick = { Toast.makeText(context, "Account Clicked", Toast.LENGTH_SHORT).show() },
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = { Text(text = "Account ") })
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun HomeBottomNavigationPreview() {
    HomeBottomNavigation()
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        modifier = modifier
            .heightIn(min = 56.dp)
            .fillMaxWidth(),
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        },
        placeholder = { Text(text = "Search") },
        colors = TextFieldDefaults.textFieldColors(
            MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
fun AlineYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes string: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(string),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, 8.dp)
        )
    }
}

@Composable
fun FavoriteCollectionCard(@DrawableRes drawable: Int, @StringRes string: Int, modifier: Modifier) {
    Surface(shape = MaterialTheme.shapes.small, modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(192.dp)
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )
            Text(text = stringResource(id = string), Modifier.padding(8.dp))
        }
    }
}

@Composable
fun HomeSection(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    composable: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = title).uppercase(Locale.getDefault()),
            Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp),
            style = MaterialTheme.typography.labelSmall
        )
        composable()
    }
}

@Preview(backgroundColor = 0xFFFFFF)
@Composable
fun HomeSectionPreview() {
    HomeSection(title = R.string.align_your_body) {
        AlineYourBodyRow()
    }
}

@Composable
fun AlineYourBodyRow(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(alineYourBodyData) { item ->
            AlineYourBodyElement(drawable = item.drawable, string = item.string)
        }
    }
}

@Composable
fun FavoriteCollectionsGrid() {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.height(140.dp)
    ) {
        items(favoriteDataCollection) { item ->
            FavoriteCollectionCard(
                drawable = item.drawable,
                string = item.string,
                modifier = Modifier
            )
        }
    }
}

@Preview(backgroundColor = 0xFFFFFF)
@Composable
fun FavoriteCollectionsGridPreview() {
    FavoriteCollectionsGrid(
        //modifier = Modifier.padding(8.dp)
    )
}

@Preview(backgroundColor = 0xFFFFFF)
@Composable
fun AlineYourBodyRowPreview() {
    AlineYourBodyRow(
        modifier = Modifier.padding(8.dp)
    )
}


@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun FavoriteCollectionCardPreview() {
    FavoriteCollectionCard(
        R.drawable.fc2_nature_meditations,
        R.string.fc2_nature_meditations,
        modifier = Modifier.padding(8.dp)
    )
}

@Preview
@Composable
fun AlineYourBodyElementPreview() {
    AlineYourBodyElement(
        R.drawable.ab1_inversions,
        R.string.ab1_inversions,
        modifier = Modifier.padding(8.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    ComposeDemoTheme {
        SearchBar(Modifier.padding(8.dp))
    }
}

