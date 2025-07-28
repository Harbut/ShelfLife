package den.harbut.shelflife.presentation.ui.screens.inspector

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import den.harbut.shelflife.domain.model.Group
import den.harbut.shelflife.domain.model.Screen
import den.harbut.shelflife.domain.model.Timer

// Тип, який може передаватися в інспектор
sealed class InspectorTarget {
    data class TimerItem(val timer: Timer) : InspectorTarget()
    data class GroupItem(val group: Group) : InspectorTarget()
    data class ScreenItem(val screen: Screen) : InspectorTarget()
}

@Composable
fun BottomInspector(
    target: InspectorTarget,
    onDismiss: () -> Unit,
    onSave: (InspectorTarget) -> Unit
) {
    var name by remember {
        mutableStateOf(
            when (target) {
                is InspectorTarget.TimerItem -> TextFieldValue(target.timer.name)
                is InspectorTarget.GroupItem -> TextFieldValue(target.group.name)
                is InspectorTarget.ScreenItem -> TextFieldValue(target.screen.name)
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = when (target) {
                is InspectorTarget.TimerItem -> "Редагування таймера"
                is InspectorTarget.GroupItem -> "Редагування групи"
                is InspectorTarget.ScreenItem -> "Редагування екрану"
            },
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Назва") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            TextButton(onClick = onDismiss) {
                Text("Скасувати")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    val updated = when (target) {
                        is InspectorTarget.TimerItem -> target.copy(timer = target.timer.copy(name = name.text))
                        is InspectorTarget.GroupItem -> target.copy(group = target.group.copy(name = name.text))
                        is InspectorTarget.ScreenItem -> target.copy(screen = target.screen.copy(name = name.text))
                    }
                    onSave(updated)
                }
            ) {
                Text("Зберегти")
            }
        }
    }
}
