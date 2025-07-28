package den.harbut.shelflife.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "screens")
data class ScreenEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String
)
