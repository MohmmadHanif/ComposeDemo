package com.example.composedemo.state

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModal: WellnessViewModal = viewModel()
) {
    Column(modifier) {
        StatefulCounter()
        WellnessTaskList(
            list = wellnessViewModal.task,
            onCloseTask = { task -> wellnessViewModal.remove(task) })
    }
}

