package unsiq.mtqmn23.jadda.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import unsiq.mtqmn23.jadda.R
import unsiq.mtqmn23.jadda.presentation.ui.theme.Green
import unsiq.mtqmn23.jadda.presentation.ui.theme.JaddaTheme
import kotlin.time.Duration.Companion.seconds

@Composable
fun SplashScreen(
    onTimeOut: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val currentOnTimeOut by rememberUpdatedState(onTimeOut)
    val systemUiController = rememberSystemUiController()

    LaunchedEffect(Unit) {
        delay(3.seconds)
        currentOnTimeOut()
    }

    DisposableEffect(Unit) {
        systemUiController.apply {
            setStatusBarColor(color = Green)
            setNavigationBarColor(color = Green)
        }
        onDispose {
            systemUiController.apply {
//                setStatusBarColor(color = Color.White)
//                setNavigationBarColor(color = Color.White)
            }
        }
    }

    Box(
        modifier = modifier.fillMaxSize()
            .background(Green)
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier.size(180.dp)
                .align(Alignment.Center)
        )
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_mosque_dark),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "by",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Light
                )
            )
            Text(
                text = "FORSIQ",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    JaddaTheme {
        SplashScreen(
            onTimeOut = {},
        )
    }
}