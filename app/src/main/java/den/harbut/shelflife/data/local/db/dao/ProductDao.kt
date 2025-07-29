package den.harbut.shelflife.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import den.harbut.shelflife.data.local.db.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getAll(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getById(id: Long): ProductEntity?

    @Insert
    suspend fun insert(product: ProductEntity)

    @Update
    suspend fun update(product: ProductEntity)

    @Query("DELETE FROM products WHERE id = :id")
    suspend fun delete(id: Long)
}
