package den.harbut.shelflife.domain.repository

import den.harbut.shelflife.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProducts(): Flow<List<Product>>
    suspend fun getProductById(id: Long): Product?
    suspend fun addProduct(product: Product)
    suspend fun deleteProduct(productId: Long)
}
