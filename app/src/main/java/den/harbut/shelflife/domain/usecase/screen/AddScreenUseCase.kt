package den.harbut.shelflife.domain.usecase.screen

import den.harbut.shelflife.domain.model.Screen
import den.harbut.shelflife.domain.repository.ScreenRepository

class AddScreenUseCase(private val repository: ScreenRepository) {
    suspend operator fun invoke(screen: Screen) = repository.addScreen(screen)
}
