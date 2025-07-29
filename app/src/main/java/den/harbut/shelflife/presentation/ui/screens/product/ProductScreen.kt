package den.harbut.shelflife.presentation.ui.screens.product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import den.harbut.shelflife.domain.model.Product
import den.harbut.shelflife.domain.util.TimeUnit
import den.harbut.shelflife.presentation.viewmodel.ProductViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    productViewModel: ProductViewModel,
    openDrawer: () -> Unit
) {
    val products by productViewModel.products.collectAsState()
    var showSheet by remember { mutableStateOf(false) }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                selectedProduct = null
                showSheet = true
            }) {
                Icon(Icons.Default.Add, contentDescription = "Create New Product")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(products) { product ->
                ProductCard(
                    product = product,
                    onEdit = {
                        selectedProduct = product
                        showSheet = true
                    }
                )
            }
        }

        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showSheet = false
                    selectedProduct = null
                }
            ) {
                ProductForm(
                    product = selectedProduct,
                    onSubmit = { updatedProduct ->
                        if (updatedProduct.id == 0L) {
                            productViewModel.addProduct(updatedProduct)
                        } else {
                            productViewModel.updateProduct(updatedProduct)
                        }
                        showSheet = false
                        selectedProduct = null
                    },
                    onDelete = {
                        selectedProduct?.let { productViewModel.deleteProduct(it.id) }
                        showSheet = false
                        selectedProduct = null
                    },
                    onCancel = {
                        showSheet = false
                        selectedProduct = null
                    }
                )
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, onEdit: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onEdit() },
        elevation = CardDefaults.cardElevation()
    ) {
        Row(
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.padding(end = 8.dp))
                Text(text = product.name)
            }

            val duration = product.timeUnit.formatDuration(product.shelfLife)
            Text(text = duration)
        }
    }
}


fun TimeUnit.formatDuration(millis: Long): String {
    val amount = when (this) {
        TimeUnit.MINUTES -> millis / (1000 * 60)
        TimeUnit.HOURS -> millis / (1000 * 60 * 60)
        TimeUnit.DAYS -> millis / (1000 * 60 * 60 * 24)
        TimeUnit.WEEKS -> millis / (1000 * 60 * 60 * 24 * 7)
        TimeUnit.MONTHS -> millis / (1000L * 60 * 60 * 24 * 30)
    }
    return "$amount ${this.name.lowercase().take(1)}"
}
