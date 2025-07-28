package den.harbut.shelflife.data.repository

import den.harbut.shelflife.data.local.db.dao.GroupDao
import den.harbut.shelflife.data.local.db.entities.GroupEntity
import den.harbut.shelflife.domain.model.Group
import den.harbut.shelflife.domain.repository.GroupRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val dao: GroupDao
) : GroupRepository {

    override fun getGroupsByScreen(screenId: Long): Flow<List<Group>> =
        dao.getByScreen(screenId).map { it.map { e -> e.toDomain() } }

    override suspend fun getGroupById(id: Long): Group? =
        dao.getById(id)?.toDomain()

    override suspend fun addGroup(group: Group) =
        dao.insert(group.toEntity())

    override suspend fun updateGroup(group: Group) =
        dao.update(group.toEntity())

    override suspend fun deleteGroup(groupId: Long) =
        dao.delete(groupId)
}

// Mapper
private fun GroupEntity.toDomain() = Group(id, name, screenId, colorHex)
private fun Group.toEntity() = GroupEntity(id, name, screenId, colorHex)
