package den.harbut.shelflife.presentation.ui.screens.editing

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import den.harbut.shelflife.domain.model.Group
import den.harbut.shelflife.domain.model.Timer

@Composable
fun GroupCard(
    group: Group,
    timers: List<Timer>,
    onDragStart: (Timer) -> Unit,
    onDrop: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(android.graphics.Color.parseColor(group.colorHex)))
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = { onDrop() },
                    onDrag = { _, _ -> },
                    onDragCancel = {}
                )
            }
            .padding(8.dp)
    ) {
        Text(text = group.name, style = MaterialTheme.typography.titleLarge)

        timers.forEach { timer ->
            DraggableTimer(timer = timer, onDragStart = onDragStart)
        }
    }
}
