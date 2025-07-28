package den.harbut.shelflife.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import den.harbut.shelflife.data.local.db.entities.ScreenEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScreenDao {
    @Query("SELECT * FROM screens")
    fun getAll(): Flow<List<ScreenEntity>>

    @Query("SELECT * FROM screens WHERE id = :id")
    suspend fun getById(id: Long): ScreenEntity?

    @Insert
    suspend fun insert(screen: ScreenEntity)

    @Query("DELETE FROM screens WHERE id = :id")
    suspend fun delete(id: Long)
}
