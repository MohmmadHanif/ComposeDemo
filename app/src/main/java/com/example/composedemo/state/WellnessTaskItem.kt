package com.example.composedemo.state

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun WellnessTaskItem(taskName: String, onClose: () -> Unit, modifier: Modifier = Modifier) {
    var checkedState by rememberSaveable { mutableStateOf(false) }

    WellnessTaskItem(
        taskName = taskName,
        checked = checkedState,
        onCheckedChange = { newState ->
            checkedState = newState
        },
        onClose = { onClose() }, modifier = modifier
    )
}

@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(
            text = taskName, modifier = modifier
                .weight(1f)
                .padding(16.dp)
        )

        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        IconButton(onClick = { onClose() }) {
            Icon(Icons.Filled.Close, contentDescription = "onClose")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun WellnessTaskItemPreview() {
    WellnessTaskItem("Hanif", true, {}, {})
}

@Preview(showBackground = true)
@Composable
fun WellnessTaskItemPreview2() {
//    WellnessTaskItem("Hanif")
}