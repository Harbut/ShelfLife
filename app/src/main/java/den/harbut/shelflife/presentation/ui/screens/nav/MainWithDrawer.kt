package den.harbut.shelflife.presentation.ui.screens.nav

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import den.harbut.shelflife.presentation.navigation.AppNavHost
import den.harbut.shelflife.presentation.ui.components.DrawerItem
import den.harbut.shelflife.presentation.ui.screens.editing.EditingActivity
import den.harbut.shelflife.presentation.viewmodel.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainWithDrawer(
    navController: NavHostController,
    timerViewModel: TimerViewModel,
    groupViewModel: GroupViewModel,
    screenViewModel: ScreenViewModel,
    productViewModel: ProductViewModel
) {
    val context = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val drawerItems = listOf(
        DrawerItem("Main", Icons.Default.Home, "main"),
        DrawerItem("Products", Icons.Default.List, "products"),
        DrawerItem("Settings", Icons.Default.Settings, "settings")
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.padding(16.dp)) {
                    Icon(Icons.Default.AccessTime, contentDescription = null, modifier = Modifier.size(40.dp))
                    Text("ShelfLife", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(16.dp))
                    drawerItems.forEach { item ->
                        NavigationDrawerItem(
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) },
                            selected = false,
                            onClick = {
                                scope.launch { drawerState.close() }
                                navController.navigate(item.route)
                            }
                        )
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("ShelfLife") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        if (currentRoute == "main") {
                            IconButton(onClick = {
                                val intent = Intent(context, EditingActivity::class.java)
                                context.startActivity(intent)
                            }) {
                                Icon(Icons.Default.Edit, contentDescription = "Edit")
                            }
                        }
                    }
                )
            }
        ) { padding ->
            AppNavHost(
                navController = navController,
                modifier = Modifier.padding(padding),
                timerViewModel = timerViewModel,
                groupViewModel = groupViewModel,
                screenViewModel = screenViewModel,
                productViewModel = productViewModel
            )
        }
    }
}
