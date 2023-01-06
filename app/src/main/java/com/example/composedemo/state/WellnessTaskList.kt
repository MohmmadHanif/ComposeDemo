package com.example.composedemo.state

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun WellnessTaskList(
    list: List<WellnessTask>, onCloseTask: (WellnessTask) -> Unit, modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    LazyColumn(modifier = modifier, state = listState) {
        items(items = list, key = { task -> task.id }) { it ->
            WellnessTaskItem(taskName = it.label, onClose = { onCloseTask(it) })
        }
    }
}

