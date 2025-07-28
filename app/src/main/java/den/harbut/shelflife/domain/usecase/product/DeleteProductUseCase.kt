package den.harbut.shelflife.domain.usecase.product

import den.harbut.shelflife.domain.repository.ProductRepository

class DeleteProductUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(productId: Long) = repository.deleteProduct(productId)
}
