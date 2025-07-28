package den.harbut.shelflife.domain.usecase.screen

import den.harbut.shelflife.domain.repository.ScreenRepository

class DeleteScreenUseCase(private val repository: ScreenRepository) {
    suspend operator fun invoke(screenId: Long) = repository.deleteScreen(screenId)
}
