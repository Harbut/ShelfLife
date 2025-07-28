package den.harbut.shelflife.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import den.harbut.shelflife.presentation.ui.screens.main.MainScreen
import den.harbut.shelflife.presentation.ui.screens.editing.EditingScreen
import den.harbut.shelflife.presentation.viewmodel.*

@Composable
fun AppNavHost(
    navController: NavHostController,
    timerViewModel: TimerViewModel,
    groupViewModel: GroupViewModel,
    screenViewModel: ScreenViewModel,
    productViewModel: ProductViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(
                timerViewModel = timerViewModel,
                groupViewModel = groupViewModel,
                screenViewModel = screenViewModel,
                onEditClick = {
                    navController.navigate("editing")
                }
            )
        }

        composable("editing") {
            EditingScreen(
                timerViewModel = timerViewModel,
                groupViewModel = groupViewModel,
                screenViewModel = screenViewModel,
                productViewModel = productViewModel
            )
        }
    }
}
