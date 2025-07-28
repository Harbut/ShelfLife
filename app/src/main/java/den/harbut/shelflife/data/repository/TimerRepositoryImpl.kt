package den.harbut.shelflife.data.repository

import den.harbut.shelflife.data.local.db.dao.TimerDao
import den.harbut.shelflife.data.local.db.entities.TimerEntity
import den.harbut.shelflife.domain.model.Timer
import den.harbut.shelflife.domain.repository.TimerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TimerRepositoryImpl(
    private val dao: TimerDao
) : TimerRepository {

    override fun getTimers(): Flow<List<Timer>> {
        return dao.getAll().map { list -> list.map { it.toDomain() } }
    }

    override fun getTimersByGroup(groupId: Long): Flow<List<Timer>> =
        dao.getByGroup(groupId).map { it.map { e -> e.toDomain() } }

    override suspend fun getTimerById(id: Long): Timer? =
        dao.getById(id)?.toDomain()

    override suspend fun addTimer(timer: Timer) =
        dao.insert(timer.toEntity())

    override suspend fun updateTimer(timer: Timer) =
        dao.update(timer.toEntity())

    override suspend fun deleteTimer(timerId: Long) =
        dao.delete(timerId)
}

// Mapper
private fun TimerEntity.toDomain() = Timer(
    id,
    productId,
    name,
    groupId,
    startTimeMillis,
    durationMillis,
    isRunning,
    pageId
)
private fun Timer.toEntity() = TimerEntity(
    id = id,
    productId = productId,
    name = name,
    groupId = groupId,
    pageId = pageId,
    startTimeMillis = startTimeMillis,
    durationMillis = durationMillis,
    isRunning = isRunning
)
