package den.harbut.shelflife.domain.model

data class Group(
    val id: Long = 0,
    val name: String,
    val screenId: Long,
    val colorHex: String = "#FFFFFF"
)
