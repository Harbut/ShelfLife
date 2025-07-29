package den.harbut.shelflife.domain.util

enum class TimeUnit(val label: String, val millisMultiplier: Long) {
    MINUTES("Хвилини", 60_000L),
    HOURS("Години", 3_600_000L),
    DAYS("Дні", 86_400_000L),
    WEEKS("Тижні", 604_800_000L),
    MONTHS("Місяці", 2_592_000_000L);

    companion object {
        fun fromLabel(label: String): TimeUnit? {
            return values().firstOrNull { it.label == label }
        }
    }

    fun toMillis(amount: Long): Long = when (this) {
        MINUTES -> amount * 60_000L
        HOURS -> amount * 60 * 60 * 1000L
        DAYS -> amount * 24 * 60 * 60 * 1000L
        WEEKS -> amount * 7 * 24 * 60 * 60 * 1000L
        MONTHS -> amount * 30 * 24 * 60 * 60 * 1000L
    }

    fun fromMillis(millis: Long): Long = when (this) {
        MINUTES -> millis / (1000 * 60)
        HOURS -> millis / (1000 * 60 * 60)
        DAYS -> millis / (1000 * 60 * 60 * 24)
        WEEKS -> millis / (1000 * 60 * 60 * 24 * 7)
        MONTHS -> millis / (1000L * 60 * 60 * 24 * 30)
    }

}
