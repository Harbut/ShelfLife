package den.harbut.shelflife.data.repository

import den.harbut.shelflife.data.local.db.dao.ScreenDao
import den.harbut.shelflife.data.local.db.entities.ScreenEntity
import den.harbut.shelflife.domain.model.Screen
import den.harbut.shelflife.domain.repository.ScreenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ScreenRepositoryImpl @Inject constructor(
    private val dao: ScreenDao
) : ScreenRepository {

    override fun getAllScreens(): Flow<List<Screen>> =
        dao.getAll().map { it.map { e -> e.toDomain() } }

    override suspend fun getScreenById(id: Long): Screen? =
        dao.getById(id)?.toDomain()

    override suspend fun addScreen(screen: Screen) =
        dao.insert(screen.toEntity())

    override suspend fun deleteScreen(screenId: Long) =
        dao.delete(screenId)
}

// Mapper
private fun ScreenEntity.toDomain() = Screen(id, name)
private fun Screen.toEntity() = ScreenEntity(id, name)
