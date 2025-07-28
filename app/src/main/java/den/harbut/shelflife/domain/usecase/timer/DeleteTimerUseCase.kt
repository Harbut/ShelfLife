package den.harbut.shelflife.domain.usecase.timer

import den.harbut.shelflife.domain.repository.TimerRepository

class DeleteTimerUseCase(private val repository: TimerRepository) {
    suspend operator fun invoke(timerId: Long) = repository.deleteTimer(timerId)
}
