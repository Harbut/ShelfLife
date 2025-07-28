package den.harbut.shelflife.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import den.harbut.shelflife.domain.model.Screen
import den.harbut.shelflife.domain.usecase.screen.AddScreenUseCase
import den.harbut.shelflife.domain.usecase.screen.DeleteScreenUseCase
import den.harbut.shelflife.domain.repository.ScreenRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ScreenViewModel(
    private val screenRepository: ScreenRepository,
    private val addScreenUseCase: AddScreenUseCase,
    private val deleteScreenUseCase: DeleteScreenUseCase
) : ViewModel() {

    private val _screens = MutableStateFlow<List<Screen>>(emptyList())
    val screens: StateFlow<List<Screen>> = _screens

    init {
        viewModelScope.launch {
            screenRepository.getAllScreens().collect {
                _screens.value = it
            }
        }
    }

    fun addScreen(screen: Screen) {
        viewModelScope.launch {
            addScreenUseCase(screen)
        }
    }

    fun deleteScreen(screenId: Long) {
        viewModelScope.launch {
            deleteScreenUseCase(screenId)
        }
    }
}
