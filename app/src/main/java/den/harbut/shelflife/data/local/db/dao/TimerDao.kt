package den.harbut.shelflife.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import den.harbut.shelflife.data.local.db.entities.TimerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TimerDao {

    @Query("SELECT * FROM timers")
    fun getAll(): Flow<List<TimerEntity>>


    @Query("SELECT * FROM timers WHERE groupId = :groupId")
    fun getByGroup(groupId: Long): Flow<List<TimerEntity>>

    @Query("SELECT * FROM timers WHERE id = :id")
    suspend fun getById(id: Long): TimerEntity?

    @Insert
    suspend fun insert(timer: TimerEntity)

    @Update
    suspend fun update(timer: TimerEntity)

    @Query("DELETE FROM timers WHERE id = :id")
    suspend fun delete(id: Long)
}
