package den.harbut.shelflife.data.mappers

import den.harbut.shelflife.data.local.db.entities.TimerEntity
import den.harbut.shelflife.domain.model.Timer

fun TimerEntity.toDomain(): Timer {
    return Timer(
        id = id,
        productId = productId,
        groupId = groupId,
        name = name,
        pageId = pageId,
        startTimeMillis = startTimeMillis,
        isRunning = isRunning,
        durationMillis = TODO()
    )
}

fun Timer.toEntity(): TimerEntity {
    return TimerEntity(
        id = id,
        productId = productId,
        groupId = groupId,
        pageId = pageId,
        startTimeMillis = startTimeMillis,
        isRunning = isRunning,
        name = TODO(),
        durationMillis = TODO()
    )
}
