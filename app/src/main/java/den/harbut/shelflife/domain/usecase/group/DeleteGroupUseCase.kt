package den.harbut.shelflife.domain.usecase.group

import den.harbut.shelflife.domain.repository.GroupRepository

class DeleteGroupUseCase(private val repository: GroupRepository) {
    suspend operator fun invoke(groupId: Long) = repository.deleteGroup(groupId)
}
