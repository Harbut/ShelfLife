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
}
