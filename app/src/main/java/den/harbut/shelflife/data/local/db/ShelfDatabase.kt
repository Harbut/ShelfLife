package den.harbut.shelflife.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import den.harbut.shelflife.data.local.db.dao.GroupDao
import den.harbut.shelflife.data.local.db.dao.ProductDao
import den.harbut.shelflife.data.local.db.dao.ScreenDao
import den.harbut.shelflife.data.local.db.dao.TimerDao
import den.harbut.shelflife.data.local.db.entities.GroupEntity
import den.harbut.shelflife.data.local.db.entities.ProductEntity
import den.harbut.shelflife.data.local.db.entities.ScreenEntity
import den.harbut.shelflife.data.local.db.entities.TimerEntity

@Database(
    entities = [
        TimerEntity::class,
        ProductEntity::class,
        GroupEntity::class,
        ScreenEntity::class
    ],
    version = 1
)
abstract class ShelfDatabase : RoomDatabase() {
    abstract fun timerDao(): TimerDao
    abstract fun productDao(): ProductDao
    abstract fun groupDao(): GroupDao
    abstract fun screenDao(): ScreenDao
}
