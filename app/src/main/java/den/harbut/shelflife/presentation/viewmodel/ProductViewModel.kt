package den.harbut.shelflife.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import den.harbut.shelflife.domain.model.Product
import den.harbut.shelflife.domain.usecase.product.AddProductUseCase
import den.harbut.shelflife.domain.usecase.product.DeleteProductUseCase
import den.harbut.shelflife.domain.usecase.product.GetAllProductsUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProductViewModel(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val addProductUseCase: AddProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    init {
        viewModelScope.launch {
            getAllProductsUseCase().collect {
                _products.value = it
            }
        }
    }

    fun addProduct(product: Product) {
        viewModelScope.launch {
            addProductUseCase(product)
        }
    }

    fun deleteProduct(id: Long) {
        viewModelScope.launch {
            deleteProductUseCase(id)
        }
    }
}
