package den.harbut.shelflife.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timers")
data class TimerEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val productId: Long,
    val name: String,
    val groupId: Long,
    val pageId: Long,
    val startTimeMillis: Long,
    val durationMillis: Long,
    val isRunning: Boolean
)
