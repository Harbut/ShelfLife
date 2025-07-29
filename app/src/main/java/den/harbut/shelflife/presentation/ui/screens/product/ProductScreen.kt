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
import den.harbut.shelflife.presentation.viewmodel.ProductViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    productViewModel: ProductViewModel,
    openDrawer: () -> Unit
) {
    val products by productViewModel.products.collectAsState()
    val scope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showSheet = true }) {
                Icon(Icons.Default.Add, contentDescription = "Create New Product")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(products) { product ->
                ProductCard(product = product)
            }
        }

        if (showSheet) {
            ProductForm(
                onSubmit = { name, durationMillis ->
                    productViewModel.addProduct(Product(name = name, defaultShelfLifeMillis = durationMillis))
                    showSheet = false
                },
                onDismiss = { showSheet = false }
            )
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
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
            Text(text = "${product.defaultShelfLifeMillis / (1000 * 60 * 60)}h")
        }
    }
}
