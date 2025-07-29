package den.harbut.shelflife.presentation.ui.screens.product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import den.harbut.shelflife.domain.model.Product
import den.harbut.shelflife.domain.util.TimeUnit

@Composable
fun ProductForm(
    product: Product? = null,
    onSubmit: (Product) -> Unit,
    onDelete: (() -> Unit)? = null,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf(product?.name ?: "") }
    var shelfLife by remember { mutableStateOf(product?.shelfLife?.toString() ?: "") }
    var timeUnit by remember { mutableStateOf(product?.timeUnit ?: TimeUnit.HOURS) }

    var nameError by remember { mutableStateOf(false) }
    var shelfLifeError by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Create/Edit Product",
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

        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = shelfLife,
                onValueChange = {
                    shelfLife = it
                    shelfLifeError = it.toIntOrNull()?.let { v -> v < 1 } ?: true
                },
                label = { Text("Shelf life") },
                isError = shelfLifeError,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            val timeUnits = TimeUnit.values()
            var expanded by remember { mutableStateOf(false) }

            Box(modifier = Modifier
                .weight(1f)
                .clickable { expanded = true }) {
                OutlinedTextField(
                    value = timeUnit.name.lowercase().replaceFirstChar(Char::titlecase),
                    onValueChange = {},
                    enabled = false,
                    label = { Text("Time Unit") },
                    modifier = Modifier.fillMaxWidth()
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    timeUnits.forEach {
                        DropdownMenuItem(
                            text = { Text(it.name.lowercase().replaceFirstChar(Char::titlecase)) },
                            onClick = {
                                timeUnit = it
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = {
                    if (product != null && onDelete != null) onDelete() else onCancel()
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text(if (product != null) "Delete" else "Cancel")
            }

            Button(
                onClick = {
                    nameError = name.isBlank()
                    shelfLifeError = shelfLife.toIntOrNull()?.let { it < 1 } ?: true

                    if (!nameError && !shelfLifeError) {
                        onSubmit(
                            product?.copy(name = name, shelfLife = shelfLife.toLong(), timeUnit = timeUnit)
                                ?: Product(name = name, shelfLife = shelfLife.toLong(), timeUnit = timeUnit)
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

