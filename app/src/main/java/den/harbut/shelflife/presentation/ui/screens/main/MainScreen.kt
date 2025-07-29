package den.harbut.shelflife.presentation.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import den.harbut.shelflife.domain.model.Timer
import den.harbut.shelflife.presentation.viewmodel.TimerViewModel
import den.harbut.shelflife.presentation.viewmodel.GroupViewModel
import den.harbut.shelflife.presentation.viewmodel.ScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    screenId: Long = 1L,
    timerViewModel: TimerViewModel,
    groupViewModel: GroupViewModel,
    screenViewModel: ScreenViewModel
) {
    val groups by groupViewModel.groups.collectAsState()
    val timers by timerViewModel.timers.collectAsState()
    val screenName by remember { mutableStateOf("Сторінка 1") }

    LaunchedEffect(Unit) {
        groupViewModel.loadGroups(screenId)
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        groups.forEach { group ->
            item {
                Text(
                    text = group.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )
            }

            val timersInGroup = timers.filter { it.groupId == group.id }

            items(timersInGroup.size) { index ->
                TimerCard(timer = timersInGroup[index])
            }
        }
    }
}
