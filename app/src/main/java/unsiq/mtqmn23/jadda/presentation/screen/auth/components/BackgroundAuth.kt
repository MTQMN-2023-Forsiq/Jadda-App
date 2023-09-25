package unsiq.mtqmn23.jadda.presentation.screen.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import unsiq.mtqmn23.jadda.R
import unsiq.mtqmn23.jadda.presentation.ui.theme.Green
import unsiq.mtqmn23.jadda.presentation.ui.theme.JaddaTheme

@Composable
fun BackgroundAuth(
    title: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxWidth()
            .height(256.dp)
            .background(Green)
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_mosque_dark),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color.White,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.align(Alignment.TopCenter)
                .padding(top = 48.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BackgroundAuthPreview() {
    JaddaTheme {
        BackgroundAuth(
            title = "Masuk Akun"
        )
    }
}