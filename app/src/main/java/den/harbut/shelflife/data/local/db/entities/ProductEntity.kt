package den.harbut.shelflife.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import den.harbut.shelflife.domain.util.TimeUnit

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val defaultShelfLifeMillis: Long,
    val timeUnit: TimeUnit = TimeUnit.HOURS // Нове поле з дефолтним значенням
)
