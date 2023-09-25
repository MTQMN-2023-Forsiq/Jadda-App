package unsiq.mtqmn23.jadda.presentation.screen.auth.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import unsiq.mtqmn23.jadda.presentation.ui.theme.JaddaTheme

@Composable
fun ButtonWithLoading(
    text: String,
    onClick : () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = Color.White,
                strokeWidth = 2.dp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(24.dp)
            )
        } else {
            Text(text)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonWithLoadingPreview() {
    JaddaTheme {
        ButtonWithLoading(
            text = "Login",
            isLoading = true,
            onClick = {},
        )
    }
}