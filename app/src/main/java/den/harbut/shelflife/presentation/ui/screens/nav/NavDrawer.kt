package den.harbut.shelflife.presentation.ui.screens.nav

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import den.harbut.shelflife.domain.model.Screen

@Composable
fun NavDrawer(
    screens: List<Screen>,
    selectedScreenId: Long?,
    onSelect: (Screen) -> Unit,
    onAddNew: () -> Unit,
    onRename: (Screen) -> Unit
) {
    Column(modifier = Modifier.fillMaxHeight().padding(8.dp)) {
        Text(
            text = "Сторінки",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)
        )

        // Список сторінок
        screens.forEach { screen ->
            val isSelected = screen.id == selectedScreenId
            NavigationDrawerItem(
                label = { Text(screen.name) },
                selected = isSelected,
                onClick = { onSelect(screen) },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Додати нову сторінку
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedButton(
            onClick = onAddNew,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Add, contentDescription = "Додати сторінку")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Нова сторінка")
        }
    }
}
