package den.harbut.shelflife.data.local.db.converters

import androidx.room.TypeConverter
import den.harbut.shelflife.domain.util.TimeUnit

class Converters {
    @TypeConverter
    fun fromTimeUnit(unit: TimeUnit): String = unit.name

    @TypeConverter
    fun toTimeUnit(name: String): TimeUnit = TimeUnit.valueOf(name)
}
