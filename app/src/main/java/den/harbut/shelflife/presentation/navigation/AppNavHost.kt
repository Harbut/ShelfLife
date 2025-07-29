package den.harbut.shelflife.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import den.harbut.shelflife.presentation.ui.screens.main.MainScreen
import den.harbut.shelflife.presentation.ui.screens.editing.EditingScreen
import den.harbut.shelflife.presentation.ui.screens.product.ProductScreen
import den.harbut.shelflife.presentation.viewmodel.*

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    timerViewModel: TimerViewModel,
    groupViewModel: GroupViewModel,
    screenViewModel: ScreenViewModel,
    productViewModel: ProductViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "main",
        modifier = modifier
    ) {
        composable("main") {
            MainScreen(
                timerViewModel = timerViewModel,
                groupViewModel = groupViewModel,
                screenViewModel = screenViewModel,
            )
        }

        composable("products") {
            ProductScreen(
                productViewModel = productViewModel,
                openDrawer = { navController.navigateUp() }
            )
        }

        composable("settings") {
            // TODO: Після реалізації SettingsScreen
            androidx.compose.material3.Text(
                text = "⚙️ Налаштування (в розробці)",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
