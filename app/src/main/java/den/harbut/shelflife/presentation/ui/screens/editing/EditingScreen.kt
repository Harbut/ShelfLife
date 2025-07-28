package den.harbut.shelflife.presentation.ui.screens.editing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import den.harbut.shelflife.domain.model.Group
import den.harbut.shelflife.domain.model.Timer
import den.harbut.shelflife.presentation.ui.components.UndoRedoButtons
import den.harbut.shelflife.presentation.viewmodel.TimerViewModel

@Composable
fun EditingScreen(
    groups: List<Group>,
    timers: List<Timer>,
    viewModel: TimerViewModel,
    onUndo: () -> Unit,
    onRedo: () -> Unit
) {
    // Стан перетягування: ID таймера та групи-призначення
    var draggedTimer by remember { mutableStateOf<Timer?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        // Кнопки undo/redo зверху
        UndoRedoButtons(onUndo = onUndo, onRedo = onRedo)

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            groups.forEach { group ->
                item {
                    GroupCard(
                        group = group,
                        timers = timers.filter { it.groupId == group.id },
                        onDragStart = { timer -> draggedTimer = timer },
                        onDrop = {
                            draggedTimer?.let { timer ->
                                // оновлюємо таймер з новою групою
                                viewModel.updateTimer(timer.copy(groupId = group.id))
                                draggedTimer = null
                            }
                        }
                    )
                }
            }
        }
    }
}
