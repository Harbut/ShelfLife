package den.harbut.shelflife.domain.usecase.group

import den.harbut.shelflife.domain.model.Group
import den.harbut.shelflife.domain.repository.GroupRepository
import kotlinx.coroutines.flow.Flow

class GetGroupsByPageUseCase(private val repository: GroupRepository) {
    operator fun invoke(screenId: Long): Flow<List<Group>> = repository.getGroupsByScreen(screenId)
}
