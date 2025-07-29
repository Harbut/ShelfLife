package den.harbut.shelflife.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import den.harbut.shelflife.domain.model.Product
import den.harbut.shelflife.domain.usecase.product.AddProductUseCase
import den.harbut.shelflife.domain.usecase.product.DeleteProductUseCase
import den.harbut.shelflife.domain.usecase.product.GetAllProductsUseCase
import den.harbut.shelflife.domain.usecase.product.GetProductByIdUseCase
import den.harbut.shelflife.domain.usecase.product.UpdateProductUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val addProductUseCase: AddProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase
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

    fun updateProduct(product: Product) {
        viewModelScope.launch {
            updateProductUseCase(product)
        }
    }

    fun deleteProduct(id: Long) {
        viewModelScope.launch {
            deleteProductUseCase(id)
        }
    }
}
