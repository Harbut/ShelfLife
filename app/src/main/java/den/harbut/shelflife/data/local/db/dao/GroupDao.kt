package den.harbut.shelflife.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import den.harbut.shelflife.data.local.db.entities.GroupEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {
    @Query("SELECT * FROM groups WHERE screenId = :screenId")
    fun getByScreen(screenId: Long): Flow<List<GroupEntity>>

    @Query("SELECT * FROM groups WHERE id = :id")
    suspend fun getById(id: Long): GroupEntity?

    @Insert
    suspend fun insert(group: GroupEntity)

    @Update
    suspend fun update(group: GroupEntity)

    @Query("DELETE FROM groups WHERE id = :id")
    suspend fun delete(id: Long)
}
