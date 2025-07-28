package den.harbut.shelflife.domain.usecase.timer

import den.harbut.shelflife.domain.model.Timer
import den.harbut.shelflife.domain.repository.TimerRepository
import kotlinx.coroutines.flow.Flow

class GetTimersByGroupUseCase(private val repository: TimerRepository) {
    operator fun invoke(groupId: Long): Flow<List<Timer>> = repository.getTimersByGroup(groupId)
}
