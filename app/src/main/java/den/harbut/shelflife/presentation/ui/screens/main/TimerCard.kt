package den.harbut.shelflife.presentation.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import den.harbut.shelflife.domain.model.Timer
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.DurationUnit

@Composable
fun TimerCard(timer: Timer) {
    val remainingTime = (timer.endTimeMillis - System.currentTimeMillis()).coerceAtLeast(0)
    val minutes = (remainingTime / 1000) / 60
    val seconds = (remainingTime / 1000) % 60

    val backgroundColor = when {
        !timer.isRunning -> MaterialTheme.colorScheme.surfaceVariant
        remainingTime < 5 * 60 * 1000 -> MaterialTheme.colorScheme.errorContainer
        remainingTime < 15 * 60 * 1000 -> MaterialTheme.colorScheme.tertiaryContainer
        else -> MaterialTheme.colorScheme.primaryContainer
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .background(backgroundColor)
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = timer.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "%02d:%02d".format(minutes, seconds),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            IconButton(onClick = { /* TODO: Inspector */ }) {
                Icon(Icons.Default.MoreVert, contentDescription = null)
            }
        }
    }
}
