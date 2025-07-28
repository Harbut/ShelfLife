package den.harbut.shelflife.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import den.harbut.shelflife.domain.model.Timer
import den.harbut.shelflife.domain.repository.TimerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
    private val timerRepository: TimerRepository
) : ViewModel() {

    private val _timers = MutableStateFlow<List<Timer>>(emptyList())
    val timers: StateFlow<List<Timer>> = _timers

    init {
        observeTimers()
    }

    private fun observeTimers() {
        viewModelScope.launch {
            timerRepository.getTimers().collectLatest {
                _timers.value = it
            }
        }
    }

    fun toggleTimer(timerId: Long) {
        viewModelScope.launch {
            val timer = timerRepository.getTimerById(timerId) ?: return@launch
            val updated = timer.copy(
                isRunning = !timer.isRunning,
                startTimeMillis = if (!timer.isRunning) System.currentTimeMillis() else timer.startTimeMillis
            )
            timerRepository.updateTimer(updated)
        }
    }

    fun addTimer(timer: Timer) {
        viewModelScope.launch {
            timerRepository.addTimer(timer)
        }
    }

    fun deleteTimer(timerId: Long) {
        viewModelScope.launch {
            timerRepository.deleteTimer(timerId)
        }
    }
}
