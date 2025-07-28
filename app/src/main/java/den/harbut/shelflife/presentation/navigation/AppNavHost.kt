
package den.harbut.shelflife.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import den.harbut.shelflife.presentation.ui.screens.main.MainScreen
import den.harbut.shelflife.presentation.viewmodel.TimerViewModel

// Навігаційний хост, який визначає маршрути між екранами
@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: TimerViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        // Основний екран, що показує таймери
        composable("main") {
            MainScreen(groupId = 1L, viewModel = viewModel)
        }

        // Інші екрани типу "редагування", "інспектор" тощо можна додати тут
    }
}