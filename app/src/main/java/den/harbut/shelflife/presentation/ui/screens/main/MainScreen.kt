package den.harbut.shelflife.presentation.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import den.harbut.shelflife.presentation.viewmodel.TimerViewModel
import den.harbut.shelflife.presentation.ui.screens.main.TimerCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    groupId: Long,
    viewModel: TimerViewModel
) {
    val timers by viewModel.timers.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTimers(groupId)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("ShelfLife") })
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(timers.size) { index ->
                    TimerCard(timer = timers[index])
                }
            }
        }
    }
}
