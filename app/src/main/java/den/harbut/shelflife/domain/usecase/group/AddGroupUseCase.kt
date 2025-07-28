package den.harbut.shelflife.domain.usecase.group

import den.harbut.shelflife.domain.model.Group
import den.harbut.shelflife.domain.repository.GroupRepository

class AddGroupUseCase(private val repository: GroupRepository) {
    suspend operator fun invoke(group: Group) = repository.addGroup(group)
}
