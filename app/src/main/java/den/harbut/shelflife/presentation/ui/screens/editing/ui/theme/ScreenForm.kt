package den.harbut.shelflife.presentation.ui.screens.editing.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import den.harbut.shelflife.domain.model.Product
import den.harbut.shelflife.domain.model.Screen
import den.harbut.shelflife.domain.util.TimeUnit

@Composable
fun ScreenForm(
    screen: Screen? = null,
    onSubmit: (Screen) -> Unit,
    onDelete: (() -> Unit)? = null,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf(screen?.name ?: "") }
    var nameError by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = (if (screen != null) "Edit screen" else "Create screen"),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = it.isBlank()
            },
            label = { Text("Name") },
            isError = nameError,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (screen != null && onDelete != null) {
                        onDelete()
                    } else {
                        onCancel()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text(if (screen != null) "Delete" else "Cancel")
            }

            Button(
                onClick = {
                    nameError = name.isBlank()

                    if (!nameError) {
                        onSubmit(
                            screen?.copy(name = name)
                                ?: Screen(name = name)
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Save")
            }
        }
    }
}