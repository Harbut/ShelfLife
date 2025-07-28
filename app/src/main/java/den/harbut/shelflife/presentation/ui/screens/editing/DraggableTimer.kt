package den.harbut.shelflife.presentation.ui.screens.editing

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import den.harbut.shelflife.domain.model.Timer

@Composable
fun DraggableTimer(
    timer: Timer,
    onDragStart: (Timer) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .pointerInput(timer.id) {
                detectDragGestures(
                    onDragStart = { onDragStart(timer) },
                    onDragEnd = {},
                    onDragCancel = {},
                    onDrag = { _, _ -> }
                )
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(12.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = timer.name, style = MaterialTheme.typography.titleMedium)

                val remaining = (timer.endTimeMillis - System.currentTimeMillis()).coerceAtLeast(0)
                val min = (remaining / 1000) / 60
                val sec = (remaining / 1000) % 60

                Text(
                    text = "%02d:%02d".format(min, sec),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Icon(Icons.Default.Undo, contentDescription = "Undo")
        }
    }
}
