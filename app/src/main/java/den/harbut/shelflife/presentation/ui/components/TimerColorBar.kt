package den.harbut.shelflife.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import den.harbut.shelflife.domain.model.Timer

@Composable
fun TimerColorBar(
    timer: Timer,
    modifier: Modifier = Modifier
) {
    val total = timer.durationMillis.toFloat().coerceAtLeast(1f) // Уникнення ділення на нуль
    val left = (timer.endTimeMillis - System.currentTimeMillis()).coerceAtLeast(0).toFloat()
    val percent = (left / total).coerceIn(0f, 1f)

    // Визначаємо колір залежно від залишку
    val color = when {
        !timer.isRunning -> Color.Gray
        percent < 0.1f -> Color.Red
        percent < 0.3f -> Color.Yellow
        else -> Color.Green
    }

    Box(
        modifier = modifier
            .height(6.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
            .background(Color.LightGray) // фоновий прогрес
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(percent)
                .background(color)
        )
    }
}
