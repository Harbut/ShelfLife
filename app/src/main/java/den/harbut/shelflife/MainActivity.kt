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
import den.harbut.shelflife.presentation.ui.screens.nav.MainWithDrawer
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


        setContent {
            ShelfLifeTheme {
                val navController = rememberNavController()

                MainWithDrawer(
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