package den.harbut.shelflife.presentation.ui.screens.product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import den.harbut.shelflife.domain.model.Product
import den.harbut.shelflife.presentation.viewmodel.ProductViewModel
import androidx.compose.ui.Alignment

@Composable
fun ProductScreen(viewModel: ProductViewModel) {
    val products by viewModel.products.collectAsState()
    var newProductName by remember { mutableStateOf("") }
    var newProductTime by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Продукти", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        // Поля для створення нового продукту
        OutlinedTextField(
            value = newProductName,
            onValueChange = { newProductName = it },
            label = { Text("Назва продукту") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = newProductTime,
            onValueChange = { newProductTime = it },
            label = { Text("Термін зберігання (хв)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                val duration = newProductTime.toLongOrNull()?.times(60_000) ?: 0L
                if (newProductName.isNotBlank() && duration > 0) {
                    viewModel.addProduct(Product(name = newProductName, defaultShelfLifeMillis = duration))
                    newProductName = ""
                    newProductTime = ""
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
            Spacer(Modifier.width(4.dp))
            Text("Додати")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(products.size) { index ->
                ProductItem(
                    product = products[index],
                    onDelete = { viewModel.deleteProduct(it.id) }
                )
            }
        }
    }
}
