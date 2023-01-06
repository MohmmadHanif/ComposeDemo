package com.example.composedemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.composedemo.main.AddDataInDataBaseActivity
import com.example.composedemo.main.ui.theme.ComposeDemoTheme

class RecyclerViewComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    PlantList(plant = plate.p)
                }
            }
        }
        plate.p()
    }
}

@Composable
fun PlantList(plant: List<Plant>) {
    val context = LocalContext.current
    Box() {
        LazyColumn() {
            items(plant) { it ->
                Surface(Modifier.clickable {
                    Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
                }) {
                    UserCard(it)
                }
            }
        }
        ExtendedFloatingActionButton(
            onClick = {
                context.startActivity(Intent(context, AddDataInDataBaseActivity::class.java))
            },
            Modifier
                .padding(20.dp)
                .align(Alignment.BottomEnd)
        ) {
            Text(text = "Extended FAB")
        }
    }
}


@Composable
fun UserCard(plant: Plant) {
    var expandedState by remember { mutableStateOf(false) }
    Surface() {
        ElevatedCard(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(20.dp)
        ) {
            Column() {
                Row(modifier = Modifier.padding(start = 20.dp, top = 20.dp)) {
                    AsyncImage(
                        model = plant.image
                            ?: Uri.parse(
                                plant.uri.toString()
                            ),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(10.dp)
                            .align(Alignment.CenterVertically)
                            .clip(
                                CircleShape
                            )
                            .border(2.dp, Color.Black, shape = CircleShape)

                    )
                    Column(
                        modifier = Modifier
                            .padding(20.dp, 10.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = plant.name, fontSize = 14.sp)
                        /*Text(
                            text = plant.description,
                            fontSize = 10.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(0.dp, 16.dp)
                        )*/

                        ElevatedButton(onClick = {
                            Log.e("TAG", "UserCard: ")
                        }, modifier = Modifier.padding(top = 20.dp)) {
                            Text(text = "Show Profile", fontSize = 8.sp)
                        }

                    }
                }

                Image(
                    modifier = Modifier
                        .fillMaxWidth()

                        .padding(vertical = 10.dp)
                        .clickable(
                            onClick = {
                                expandedState = !expandedState
                            }
                        ), alignment = Alignment.Center,
                    imageVector = if (expandedState) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )
                AnimatedVisibility(
                    visible = expandedState,
                    enter = expandIn() + expandVertically(),
                    exit = shrinkOut() + shrinkVertically(),
                ) {
                    Text(
                        text = plant.description,
                        modifier = Modifier.padding(12.dp),
                        fontSize = 12.sp,
                        textAlign = TextAlign.Justify
                    )

                }
            }
        }
    }
}
data class Plant(
    val id: Int,
    val name: String,
    val description: String,
    val image: Int?,
    val uri: Uri? = null
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ComposeDemoTheme {
        PlantList(plant = plate.p)
    }
}

//List
object plate {
    val p: MutableList<Plant> = mutableListOf<Plant>()
    val demo =
        listOf(
            Plant(
                1,
                "Aloe Vera",
                "It is used in the pharmaceutical industry for its healing properties and it can often be found in several cosmetic products as well.",
                R.drawable.aloe_vera,
            ),
            Plant(
                2,
                "Rose",
                "Rose is a valuable natural cosmetic ingredient. It contains antibacterial and antioxidants. It's toning and relaxing.",
                R.drawable.rose,
            ),
            Plant(
                3,
                "Calendula",
                "Calendula helps reducing redness and healing up the skin and it is considered a medicinal plant.",
                R.drawable.calendula,
            ),
            Plant(
                4,
                "Lavender",
                "The lavender blossoms contain nurturing essential oils for the skin which have a balancing and soothing fragrance.",
                R.drawable.lavender,
            ),
            Plant(
                5,
                "Jojoba",
                "Jojoba improves skin elasticity and prevents skin dehydration, making it perfect for sensitive or very dry skin.",
                R.drawable.jojoba,
            ),
            Plant(
                6,
                "Tea Tree",
                "Tea tree is aimed to fight against acne and to treat acne prone skin. It’s considered one of the best natural alternatives to chemical substances.",
                R.drawable.tea_tree,
            ),
            Plant(
                7,
                "Chamomile",
                "Chamomile is the most extensive applications in natural cosmetics. It is a common flavoring agent in foods and beverages, and other products such as mouthwash, soaps, and cosmetics.",
                R.drawable.chamomile,
            ),
            Plant(
                8,
                "Rosemary",
                "Rosemary (Rosmarinus officinalis) is deep green in colour and pungent in fragrance.",
                R.drawable.rosemary,
            ),
            Plant(
                9,
                "Kiwi",
                "It contains high amounts of vitamin C and a potent antioxidant that can protect the skin from free radicals, stimulate collagen production, and reduce hyper pigmentation.",
                R.drawable.kiwi,
            ),
            Plant(
                10,
                "Cilantro",
                "Cilantro is high in vitamin C, an antioxidant that fights off damage-causing free radicals.",
                R.drawable.cilantro,
            ),
            Plant(
                11,
                "Orange",
                "Consuming enough vitamin C can help a person maintain skin health and appearance.  Vitamin C contributes to collagen production. Collagen supports the skin, promotes wound healing, and improves skin strength.",
                R.drawable.orange,
            ),
            Plant(
                12,
                "Cucumber",
                "Cucumber is rich in all most Vitamin (C,A,B,K). Magnesium in cucumbers can promote skin elasticity and retain skin moisture.Potassium in cucumbers is another mineral that can help in hydrating the skin and balancing the electrolytes.",
                R.drawable.cucumber,
            ),
        )

    fun p() {
        p.addAll(demo)
    }

    fun pAdd(plant: Plant) {
        p.add(plant)
    }

/*
        listOf(
        Plant(
            1,
            "Aloe Vera",
            "It is used in the pharmaceutical industry for its healing properties and it can often be found in several cosmetic products as well.",
            R.drawable.aloe_vera,
        ),
        Plant(
            2,
            "Rose",
            "Rose is a valuable natural cosmetic ingredient. It contains antibacterial and antioxidants. It's toning and relaxing.",
            R.drawable.rose,
        ),
        Plant(
            3,
            "Calendula",
            "Calendula helps reducing redness and healing up the skin and it is considered a medicinal plant.",
            R.drawable.calendula,
        ),
        Plant(
            4,
            "Lavender",
            "The lavender blossoms contain nurturing essential oils for the skin which have a balancing and soothing fragrance.",
            R.drawable.lavender,
        ),
        Plant(
            5,
            "Jojoba",
            "Jojoba improves skin elasticity and prevents skin dehydration, making it perfect for sensitive or very dry skin.",
            R.drawable.jojoba,
        ),
        Plant(
            6,
            "Tea Tree",
            "Tea tree is aimed to fight against acne and to treat acne prone skin. It’s considered one of the best natural alternatives to chemical substances.",
            R.drawable.tea_tree,
        ),
        Plant(
            7,
            "Chamomile",
            "Chamomile is the most extensive applications in natural cosmetics. It is a common flavoring agent in foods and beverages, and other products such as mouthwash, soaps, and cosmetics.",
            R.drawable.chamomile,
        ),
        Plant(
            8,
            "Rosemary",
            "Rosemary (Rosmarinus officinalis) is deep green in colour and pungent in fragrance.",
            R.drawable.rosemary,
        ),
        Plant(
            9,
            "Kiwi",
            "It contains high amounts of vitamin C and a potent antioxidant that can protect the skin from free radicals, stimulate collagen production, and reduce hyper pigmentation.",
            R.drawable.kiwi,
        ),
        Plant(
            10,
            "Cilantro",
            "Cilantro is high in vitamin C, an antioxidant that fights off damage-causing free radicals.",
            R.drawable.cilantro,
        ),
        Plant(
            11,
            "Orange",
            "Consuming enough vitamin C can help a person maintain skin health and appearance.  Vitamin C contributes to collagen production. Collagen supports the skin, promotes wound healing, and improves skin strength.",
            R.drawable.orange,
        ),
        Plant(
            12,
            "Cucumber",
            "Cucumber is rich in all most Vitamin (C,A,B,K). Magnesium in cucumbers can promote skin elasticity and retain skin moisture.Potassium in cucumbers is another mineral that can help in hydrating the skin and balancing the electrolytes.",
            R.drawable.cucumber,
        ),
    )*/
}