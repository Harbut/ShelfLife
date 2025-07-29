package den.harbut.shelflife.domain.model

import den.harbut.shelflife.domain.util.TimeUnit

data class Product(
    val id: Long = 0L,
    val name: String,
    val shelfLife: Long, // В мілісекундах
    val timeUnit: TimeUnit
)
