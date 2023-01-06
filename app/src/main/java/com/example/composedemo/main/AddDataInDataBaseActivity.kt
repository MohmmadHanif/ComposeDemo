@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.composedemo.main

import android.app.Activity
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.*
import com.example.composedemo.Plant
import com.example.composedemo.R
import com.example.composedemo.main.ui.theme.ComposeDemoTheme
import com.example.composedemo.plate

class AddDataInDataBaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                ) {
                    Column( modifier = Modifier
                        .verticalScroll(rememberScrollState())) {
                        LottieImage()
                        //  PlantFrom()
                    }
                }
            }
        }
    }
}

val poppine = FontFamily(
    Font(R.font.poppins)
)

@Composable
fun LottieImage() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        val context = LocalContext.current as Activity
        val name = remember { mutableStateOf(TextFieldValue()) }
        val description = remember { mutableStateOf(TextFieldValue()) }
        Column(
            modifier = Modifier.background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
           /* val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.))
            (composition, iterations = LottieConstants.IterateForever)*/

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 100.dp))
                    .background(Color.White)
                    .fillMaxSize(),
            ) {

                Text(
                    text = "Insert Plant Details",
                    fontSize = 20.sp,
                    fontFamily = poppine,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 30.dp, end = 30.dp, top = 50.dp, bottom = 10.dp)
                        .wrapContentHeight()
                )

                OutlinedTextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp),
                    placeholder = { Text(text = "Enter Plant Name") },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text
                    ),
                    textStyle = TextStyle(color = Color.Black, fontFamily = FontFamily.SansSerif),
                    maxLines = 1,
                    label = {
                        Text(text = "Name")
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.growing_plant_svgrepo_com),
                            contentDescription = ""
                        )
                    }
                )

                /** * Password*/

                OutlinedTextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp),
                    placeholder = { Text(text = "Enter Description") },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text
                    ),
                    textStyle = TextStyle(color = Color.Black, fontFamily = FontFamily.SansSerif),
                    maxLines = 3,
                    label = {
                        Text(text = "Description")
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_text_snippet_24),
                            contentDescription = "", Modifier.size(24.dp)
                        )
                    }
                )

                val imageData: MutableState<Uri?> = remember { mutableStateOf(null) }
                val isResult = remember {
                    mutableStateOf(false)
                }

                val launcher =
                    rememberLauncherForActivityResult(
                        ActivityResultContracts.GetContent(),
                        onResult = {
                            imageData.value = it
                            isResult.value = true
                        })

                AsyncImage(
                    modifier = Modifier
                        .size(140.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            launcher.launch("image/*")
                        },
                    model = if (imageData.value != null) imageData.value else R.drawable.add_photo,
                    contentDescription = "",
                )
                if (!(isResult.value)) {
                    Text("Please Select Image !",
                        fontFamily = poppine,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clickable { launcher.launch("image/*") }
                    )
                }


                Button(
                    onClick = {
                        context.finish()
                        plate.pAdd(
                            Plant(
                                0,
                                name.value.text,
                                description = description.value.text,
                                null,
                                imageData.value
                            )
                        )
                    },
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth().wrapContentHeight()
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    Text(text = "Add Plant")
                }
            }
        }
    }
}


@Preview("Light")
@Preview(
    "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun DefaultPreview3() {
    ComposeDemoTheme {
        LottieImage()
    }
}