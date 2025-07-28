package den.harbut.shelflife.presentation.viewmodel

import den.harbut.shelflife.domain.model.Timer

data class UiState(
    val timers: List<Timer> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
