package den.harbut.shelflife.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import den.harbut.shelflife.R
import den.harbut.shelflife.domain.model.Timer

// Іконка та текстовий опис статусу таймера
@Composable
fun TimerStatusIcon(timer: Timer) {
    val remainingTime = (timer.endTimeMillis - System.currentTimeMillis()).coerceAtLeast(0)

    // Визначаємо іконку, текст і колір залежно від стану
    val (iconId, label, color) = when {
        !timer.isRunning -> Triple(R.drawable.ic_pause, "Зупинений", Color.Gray)
        remainingTime < 5 * 60 * 1000 -> Triple(R.drawable.ic_alert, "Критично", Color.Red)
        remainingTime < 15 * 60 * 1000 -> Triple(R.drawable.ic_warning, "Увага", Color.Yellow)
        else -> Triple(R.drawable.ic_check, "OK", Color.Green)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
