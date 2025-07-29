package den.harbut.shelflife.domain.usecase.product

import den.harbut.shelflife.domain.model.Product
import den.harbut.shelflife.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: Long): Product? {
        return repository.getProductById(id)
    }
}
