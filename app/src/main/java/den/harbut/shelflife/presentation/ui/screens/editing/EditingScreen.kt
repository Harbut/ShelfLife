package den.harbut.shelflife.presentation.ui.screens.editing

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.ScreenShare
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import den.harbut.shelflife.domain.model.Group
import den.harbut.shelflife.domain.model.Screen
import den.harbut.shelflife.domain.model.Timer
import den.harbut.shelflife.presentation.viewmodel.GroupViewModel
import den.harbut.shelflife.presentation.viewmodel.ProductViewModel
import den.harbut.shelflife.presentation.viewmodel.ScreenViewModel
import den.harbut.shelflife.presentation.viewmodel.TimerViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditingScreen(
    screenViewModel: ScreenViewModel,
    groupViewModel: GroupViewModel,
    timerViewModel: TimerViewModel,
    productViewModel: ProductViewModel
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var sheetContent by remember { mutableStateOf<@Composable () -> Unit>({}) }
    var showSheet by remember { mutableStateOf(false) }

    val fabItems = listOf(
        FabItem("Screen", Icons.Default.ScreenShare) {
            sheetContent = { CreateScreenForm(
                onSubmit = { name ->
                    screenViewModel.addScreen(Screen(name = name))
                    showSheet = false
                },
                onDismiss = {
                    showSheet = false
                }
            )}
            showSheet = true
        },
        FabItem("Group", Icons.Default.Group) {
            sheetContent = { CreateGroupForm(
                screenId = 1L,
                onSubmit = { name, screenId ->
                    groupViewModel.addGroup(Group(name = name, screenId = screenId))
                    showSheet = false
                },
                onDismiss = {
                    showSheet = false
                }) }
            showSheet = true
        },
        FabItem("Timer", Icons.Default.Timer) {
            sheetContent = { CreateTimerForm(
                groupId = 1L, // 🔧 Поки що хардкод, пізніше зробимо вибір
                onSubmit = { timer ->
                    timerViewModel.addTimer(timer)
                    showSheet = false
                },
                onDismiss = {
                    showSheet = false
                }
            ) }
            showSheet = true
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editing") },
                actions = {
                    IconButton(onClick = { /* Optional edit action */ }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                    }
                }
            )
        },
        floatingActionButton = {
            ExpandableFab(fabItems)
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            Text("Editing screen content goes here", modifier = Modifier.align(Alignment.Center))
        }

        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = { showSheet = false },
                sheetState = sheetState
            ) {
                sheetContent()
            }
        }
    }
}

@Composable
fun ScreenBottomSheetContent() {
    Column(Modifier.padding(16.dp)) {
        Text("Додавання нового екрану", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Тут буде форма для створення Screen")
    }
}

@Composable
fun GroupBottomSheetContent() {
    Column(Modifier.padding(16.dp)) {
        Text("Додавання нової групи", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Тут буде форма для створення Group")
    }
}

@Composable
fun TimerBottomSheetContent() {
    Column(Modifier.padding(16.dp)) {
        Text("Додавання нового таймера", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Тут буде форма для створення Timer")
    }
}

data class FabItem(
    val label: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

@Composable
fun ExpandableFab(items: List<FabItem>) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 16.dp, bottom = 16.dp)
    ) {
        if (expanded) {
            items.forEach { item ->
                ExtendedFloatingActionButton(
                    onClick = {
                        item.onClick()
                        expanded = false
                    },
                    icon = { Icon(item.icon, contentDescription = item.label) },
                    text = { Text(item.label) },
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }

        FloatingActionButton(
            onClick = { expanded = !expanded }
        ) {
            Icon(
                imageVector = if (expanded) Icons.Default.Edit else Icons.Default.Edit,
                contentDescription = "Toggle FAB"
            )
        }
    }
}


@Composable
fun CreateScreenForm(
    onSubmit: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Нова сторінка", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Назва сторінки") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            TextButton(onClick = onDismiss) {
                Text("Скасувати")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (name.isNotBlank()) onSubmit(name)
                    onDismiss()
                }
            ) {
                Text("Зберегти")
            }
        }
    }
}

@Composable
fun CreateGroupForm(
    screenId: Long,
    onSubmit: (String, Long) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Нова група", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Назва групи") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            TextButton(onClick = onDismiss) {
                Text("Скасувати")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (name.isNotBlank()) onSubmit(name, screenId)
                    onDismiss()
                }
            ) {
                Text("Зберегти")
            }
        }
    }
}

@Composable
fun CreateTimerForm(
    groupId: Long,
    onSubmit: (Timer) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var durationMinutes by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Новий таймер", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Назва таймера") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = durationMinutes,
            onValueChange = { durationMinutes = it.filter { ch -> ch.isDigit() } },
            label = { Text("Тривалість (хвилин)") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            TextButton(onClick = onDismiss) {
                Text("Скасувати")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    val duration = durationMinutes.toLongOrNull()?.times(60_000)
                    if (name.isNotBlank() && duration != null) {
                        onSubmit(
                            Timer(
                                name = name,
                                groupId = groupId,
                                productId = 0L, // додай прив'язку пізніше
                                pageId = 1L,
                                startTimeMillis = System.currentTimeMillis(),
                                durationMillis = duration
                            )
                        )
                    }
                    onDismiss()
                }
            ) {
                Text("Зберегти")
            }
        }
    }
}

