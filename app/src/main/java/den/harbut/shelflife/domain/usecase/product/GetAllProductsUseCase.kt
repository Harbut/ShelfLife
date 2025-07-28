package den.harbut.shelflife.domain.usecase.product

import den.harbut.shelflife.domain.model.Product
import den.harbut.shelflife.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetAllProductsUseCase(private val repository: ProductRepository) {
    operator fun invoke(): Flow<List<Product>> = repository.getAllProducts()
}
