package den.harbut.shelflife.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Redo
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Кнопки для скасування та повтору дій (Undo/Redo)
@Composable
fun UndoRedoButtons(
    onUndo: () -> Unit,
    onRedo: () -> Unit
) {
    Row(modifier = Modifier.padding(8.dp)) {
        IconButton(onClick = onUndo) {
            Icon(Icons.Default.Undo, contentDescription = "Undo")
        }
        IconButton(onClick = onRedo) {
            Icon(Icons.Default.Redo, contentDescription = "Redo")
        }
    }
}
