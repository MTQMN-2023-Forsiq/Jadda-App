package unsiq.mtqmn23.jadda.presentation.screen.compass.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import unsiq.mtqmn23.jadda.R

@Composable
fun Compass(
    direction: Int?,
    rotation: Int,
    modifier: Modifier = Modifier,
) {
    val (lastRotation, setLastRotation) = remember { mutableStateOf(0) }
    var newRotation = lastRotation
    val modLast = if (lastRotation > 0) lastRotation % 360 else 360 - (-lastRotation % 360)

    if (modLast != rotation) {
        val backward = if (rotation > modLast) modLast + 360 - rotation else modLast - rotation
        val forward = if (rotation > modLast) rotation - modLast else 360 - modLast + rotation

        newRotation = if (backward < forward) lastRotation - backward
        else lastRotation + forward

        setLastRotation(newRotation)
    }

    val angle by animateFloatAsState(
        targetValue = -newRotation.toFloat(),
        animationSpec = tween(
            durationMillis = 250,
            easing = LinearEasing
        ),
        label = ""
    )

    Box(modifier = modifier) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_compass_rose),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(328.dp)
                .rotate(angle)
        )
        if (direction != null) {
            DirectionPointer(
                angle = angle + direction.toFloat(),
                modifier = Modifier
                    .size(364.dp)
                    .align(Alignment.Center)
            )
        }
    }
}