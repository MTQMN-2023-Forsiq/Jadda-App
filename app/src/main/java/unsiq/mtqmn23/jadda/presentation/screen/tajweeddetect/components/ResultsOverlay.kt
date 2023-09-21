package unsiq.mtqmn23.jadda.presentation.screen.tajweeddetect.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.mediapipe.tasks.vision.objectdetector.ObjectDetectionResult
import unsiq.mtqmn23.jadda.presentation.ui.theme.Green
import unsiq.mtqmn23.jadda.presentation.ui.theme.Yellow

@Composable
fun ResultsOverlay(
    results: ObjectDetectionResult,
    frameWidth: Int,
    frameHeight: Int,
    onClick: (String) -> Unit,
) {
    val detections = results.detections()
    if (detections != null) {
        for (detection in detections) {
            BoxWithConstraints(
                Modifier
                    .fillMaxSize()
            ) {
                // calculating the UI dimensions of the detection bounds
                val resultBounds = detection.boundingBox()
                val boxWidth = (resultBounds.width() / frameWidth) * this.maxWidth.value
                val boxHeight = (resultBounds.height() / frameHeight) * this.maxHeight.value
                val boxLeftOffset = (resultBounds.left / frameWidth) * this.maxWidth.value
                val boxTopOffset = (resultBounds.top / frameHeight) * this.maxHeight.value
                val boundsColor = when (detection.categories().first().categoryName()) {
                    "35" -> Green
                    else -> Yellow
                }

                Box(
                    Modifier
                        .fillMaxSize()
                        .offset(
                            boxLeftOffset.dp,
                            boxTopOffset.dp,
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = boundsColor)
                            .width(boxWidth.dp)
                            .height(boxHeight.dp)
                            .clickable {
                                onClick(detection.categories().first().categoryName())
                            }
                    )
                }
            }
        }
    }
}