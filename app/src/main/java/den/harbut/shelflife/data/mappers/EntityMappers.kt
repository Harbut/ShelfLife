package den.harbut.shelflife.data.mappers

import den.harbut.shelflife.data.local.db.entities.TimerEntity
import den.harbut.shelflife.domain.model.Timer

fun TimerEntity.toDomain(): Timer {
    return Timer(
        id = id,
        productId = productId,
        name = name,
        groupId = groupId,
        startTimeMillis = startTimeMillis,
        durationMillis = durationMillis,
        isRunning = isRunning,
        pageId = pageId
    )
}

fun Timer.toEntity(): TimerEntity {
    return TimerEntity(
        id = id,
        productId = productId,
        name = name,
        groupId = groupId,
        pageId = pageId,
        startTimeMillis = startTimeMillis,
        durationMillis = durationMillis,
        isRunning = isRunning
    )
}
