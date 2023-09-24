package unsiq.mtqmn23.jadda.presentation.screen.compass.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import unsiq.mtqmn23.jadda.R
import unsiq.mtqmn23.jadda.presentation.ui.theme.JaddaTheme

@Composable
fun DirectionPointer(
    angle: Float,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .rotate(angle)
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_navigation),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center)
        )
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_pointer),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DirectionPointerPreview() {
    JaddaTheme {
        DirectionPointer(
            angle = 0.35f
        )
    }
}