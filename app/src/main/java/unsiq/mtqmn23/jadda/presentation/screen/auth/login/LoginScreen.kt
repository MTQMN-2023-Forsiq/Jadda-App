package unsiq.mtqmn23.jadda.presentation.screen.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import unsiq.mtqmn23.jadda.presentation.screen.auth.components.BackgroundAuth
import unsiq.mtqmn23.jadda.presentation.screen.auth.components.CardAuth
import unsiq.mtqmn23.jadda.presentation.ui.theme.Green
import unsiq.mtqmn23.jadda.presentation.ui.theme.JaddaTheme

@Composable
fun LoginScreen() {
    LoginContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(modifier: Modifier = Modifier) {
    val systemUiController = rememberSystemUiController()

    DisposableEffect(Unit) {
        systemUiController.apply {
            setStatusBarColor(color = Green)
            setNavigationBarColor(color = Green)
        }
        onDispose {
            systemUiController.apply {
                setStatusBarColor(color = Color.White)
                setNavigationBarColor(color = Color.White)
            }
        }
    }

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (bgGreen, cvForm, tvRegister) = createRefs()

        BackgroundAuth(
            modifier = Modifier
                .constrainAs(bgGreen) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            title = "Masuk Akun"
        )

        CardAuth(
            inputForm = {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text("Email")
                    },
                    modifier = Modifier.padding(top = 16.dp)
                        .padding(16.dp)
                        .fillMaxWidth()
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text("Password")
                    },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = "Lupa password?",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Green
                    ),
                    modifier = Modifier.padding(16.dp)
                        .align(Alignment.End)
                        .clickable { }
                )
            },
            titleButton = "Login",
            onMainButtonClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(cvForm) {
                    top.linkTo(parent.top, margin = 160.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginContentPreview() {
    JaddaTheme {
        LoginContent()
    }
}