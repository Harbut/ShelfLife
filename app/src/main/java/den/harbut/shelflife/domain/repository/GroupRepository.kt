package den.harbut.shelflife.domain.repository

import den.harbut.shelflife.domain.model.Group
import kotlinx.coroutines.flow.Flow

interface GroupRepository {
    fun getGroupsByScreen(screenId: Long): Flow<List<Group>>
    suspend fun getGroupById(id: Long): Group?
    suspend fun addGroup(group: Group)
    suspend fun updateGroup(group: Group)
    suspend fun deleteGroup(groupId: Long)
}
