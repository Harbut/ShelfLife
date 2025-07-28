package den.harbut.shelflife.domain.usecase.product

import den.harbut.shelflife.domain.model.Product
import den.harbut.shelflife.domain.repository.ProductRepository

class AddProductUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(product: Product) = repository.addProduct(product)
}
