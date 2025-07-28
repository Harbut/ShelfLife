package den.harbut.shelflife.domain.repository

import den.harbut.shelflife.domain.model.Timer
import kotlinx.coroutines.flow.Flow

interface TimerRepository {
    fun getTimers(): Flow<List<Timer>>
    fun getTimersByGroup(groupId: Long): Flow<List<Timer>>
    suspend fun getTimerById(id: Long): Timer?
    suspend fun addTimer(timer: Timer)
    suspend fun updateTimer(timer: Timer)
    suspend fun deleteTimer(timerId: Long)
}
