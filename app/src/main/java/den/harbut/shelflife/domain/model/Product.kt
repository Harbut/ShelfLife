package den.harbut.shelflife.domain.model

data class Product(
    val id: Long = 0,
    val name: String,
    val defaultShelfLifeMillis: Long
)
