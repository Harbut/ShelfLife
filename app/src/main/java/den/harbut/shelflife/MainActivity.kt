package den.harbut.shelflife

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import den.harbut.shelflife.presentation.navigation.AppNavHost
import den.harbut.shelflife.presentation.ui.theme.ShelfLifeTheme
import den.harbut.shelflife.presentation.viewmodel.*
import den.harbut.shelflife.domain.model.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // ViewModel-и, автоматично підключаються через Hilt
    private val timerViewModel: TimerViewModel by viewModels()
    private val productViewModel: ProductViewModel by viewModels()
    private val groupViewModel: GroupViewModel by viewModels()
    private val screenViewModel: ScreenViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Temporary test data for UI verification
        screenViewModel.addScreen(Screen(id = 1, name = "Test Screen"))
        groupViewModel.addGroup(Group(id = 1, name = "Test Group", screenId = 1))
        productViewModel.addProduct(
            Product(
                id = 1,
                name = "Test Product",
                defaultShelfLifeMillis = 7L * 24 * 60 * 60 * 1000
            )
        )
        timerViewModel.addTimer(
            Timer(
                id = 1,
                productId = 1,
                name = "Sample Timer",
                groupId = 1,
                startTimeMillis = System.currentTimeMillis(),
                durationMillis = 10L * 60 * 1000,
                pageId = 1
            )
        )

        setContent {
            ShelfLifeTheme {
                val navController = rememberNavController()

                Scaffold {
                    AppNavHost(
                        navController = navController,
                        timerViewModel = timerViewModel,
                        groupViewModel = groupViewModel,
                        screenViewModel = screenViewModel,
                        productViewModel = productViewModel
                    )
                }
            }
        }
    }
}