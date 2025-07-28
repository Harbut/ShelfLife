package den.harbut.shelflife.domain.usecase.timer

import den.harbut.shelflife.domain.model.Timer
import den.harbut.shelflife.domain.repository.TimerRepository

class StartStopTimerUseCase(private val repository: TimerRepository) {
    suspend operator fun invoke(timer: Timer, start: Boolean) {
        val updated = timer.copy(
            isRunning = start,
            startTimeMillis = if (start) System.currentTimeMillis() else timer.startTimeMillis
        )
        repository.updateTimer(updated)
    }
}
