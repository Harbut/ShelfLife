package den.harbut.shelflife.data.repository

import den.harbut.shelflife.data.local.db.dao.ProductDao
import den.harbut.shelflife.data.local.db.entities.ProductEntity
import den.harbut.shelflife.domain.model.Product
import den.harbut.shelflife.domain.repository.ProductRepository
import den.harbut.shelflife.domain.util.TimeUnit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val dao: ProductDao
) : ProductRepository {

    override fun getAllProducts(): Flow<List<Product>> =
        dao.getAll().map { it.map { e -> e.toDomain() } }

    override suspend fun getProductById(id: Long): Product? =
        dao.getById(id)?.toDomain()

    override suspend fun addProduct(product: Product) =
        dao.insert(product.toEntity())

    override suspend fun updateProduct(product: Product) {
        dao.update(product.toEntity())
    }

    override suspend fun deleteProduct(productId: Long) =
        dao.delete(productId)
}

// 🧠 Мапери

private fun ProductEntity.toDomain(): Product = Product(
    id = id,
    name = name,
    shelfLife = defaultShelfLifeMillis,
    timeUnit = timeUnit // ← нове поле
)

private fun Product.toEntity(): ProductEntity = ProductEntity(
    id = id,
    name = name,
    defaultShelfLifeMillis = shelfLife,
    timeUnit = timeUnit // ← нове поле
)
