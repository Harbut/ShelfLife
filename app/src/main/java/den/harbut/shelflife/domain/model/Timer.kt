package den.harbut.shelflife.domain.model

data class Timer(
    val id: Long = 0,
    val productId: Long,
    val name: String,
    val groupId: Long,
    val startTimeMillis: Long,
    val durationMillis: Long,
    val isRunning: Boolean = true,
    val pageId: Long
) {
    val endTimeMillis: Long
        get() = startTimeMillis + durationMillis
}
