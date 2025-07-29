package den.harbut.shelflife.presentation.ui.screens.editing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import den.harbut.shelflife.presentation.ui.theme.ShelfLifeTheme
import den.harbut.shelflife.presentation.viewmodel.*

@AndroidEntryPoint
class EditingActivity : ComponentActivity() {

    private val timerViewModel: TimerViewModel by viewModels()
    private val groupViewModel: GroupViewModel by viewModels()
    private val screenViewModel: ScreenViewModel by viewModels()
    private val productViewModel: ProductViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShelfLifeTheme {
                var showSaveConfirmation by remember { mutableStateOf(false) }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Редагування") },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                                }
                            },
                            actions = {
                                IconButton(onClick = {
                                    showSaveConfirmation = true
                                }) {
                                    Icon(Icons.Default.Save, contentDescription = "Зберегти")
                                }
                            }
                        )
                    }
                ) { padding ->
                    EditingScreen(
                        timerViewModel = timerViewModel,
                        groupViewModel = groupViewModel,
                        screenViewModel = screenViewModel,
                        productViewModel = productViewModel,
                        modifier = Modifier.padding(padding)
                    )
                }

                if (showSaveConfirmation) {
                    AlertDialog(
                        onDismissRequest = { showSaveConfirmation = false },
                        confirmButton = {
                            TextButton(onClick = {
                                // Тут можна викликати будь-яку логіку збереження, якщо потрібно
                                showSaveConfirmation = false
                                finish()
                            }) {
                                Text("Так")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = {
                                showSaveConfirmation = false
                            }) {
                                Text("Скасувати")
                            }
                        },
                        title = { Text("Зберегти зміни?") },
                        text = { Text("Всі зміни вже збережено автоматично, але ти можеш переконатись, що все ок.") }
                    )
                }
            }
        }
    }
}
