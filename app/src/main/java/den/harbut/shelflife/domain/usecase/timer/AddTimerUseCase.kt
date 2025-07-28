package den.harbut.shelflife.domain.usecase.timer

import den.harbut.shelflife.domain.model.Timer
import den.harbut.shelflife.domain.repository.TimerRepository

class AddTimerUseCase(private val repository: TimerRepository) {
    suspend operator fun invoke(timer: Timer) = repository.addTimer(timer)
}
