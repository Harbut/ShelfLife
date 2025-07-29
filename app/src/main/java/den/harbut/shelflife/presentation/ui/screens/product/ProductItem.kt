package den.harbut.shelflife.presentation.ui.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import den.harbut.shelflife.domain.model.Product

@Composable
fun ProductItem(
    product: Product,
    onDelete: (Product) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(12.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = product.name, style = MaterialTheme.typography.titleMedium)
                val minutes = product.shelfLife / 60_000
                Text(text = "Зберігати: $minutes хв", style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = { onDelete(product) }) {
                Icon(Icons.Default.Delete, contentDescription = "Видалити")
            }
        }
    }
}
