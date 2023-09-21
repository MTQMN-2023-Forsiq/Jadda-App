package unsiq.mtqmn23.jadda.presentation.screen.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import unsiq.mtqmn23.jadda.presentation.screen.auth.components.BackgroundAuth
import unsiq.mtqmn23.jadda.presentation.screen.auth.components.CardAuth
import unsiq.mtqmn23.jadda.presentation.screen.auth.components.SpannableText
import unsiq.mtqmn23.jadda.presentation.ui.theme.Green
import unsiq.mtqmn23.jadda.presentation.ui.theme.JaddaTheme

@Composable
fun LoginScreen(
    snackBarHostState: SnackbarHostState,
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    state.statusMessage?.let {
        LaunchedEffect(it) {
            snackBarHostState.showSnackbar(it)
        }
    }
    LoginContent(
        email = state.email,
        password = state.password,
        navigateToRegister = navigateToRegister,
        navigateToHome = navigateToHome,
        doLogin = {
            viewModel.onEvent(LoginEvent.OnLogin(state.email, state.password))
        },
        onValueChanged = { email, password ->
            viewModel.onEvent(LoginEvent.OnValueChanged(email, password))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    email: String,
    password: String,
    modifier: Modifier = Modifier,
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit,
    onValueChanged: (email: String, password: String) -> Unit,
    doLogin: () -> Unit,
) {

    ConstraintLayout(
        modifier = modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
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
                    value = email,
                    onValueChange = {
                        onValueChanged(it, password)
                    },
                    placeholder = {
                        Text("Email")
                    },
                    modifier = Modifier.padding(top = 16.dp)
                        .padding(16.dp)
                        .fillMaxWidth()
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        onValueChanged(email, it)
                    },
                    placeholder = {
                        Text("Password")
                    },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = "Lupa password?",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Green
                    ),
                    modifier = Modifier.padding(16.dp)
                        .align(Alignment.End)
                        .clickable { doLogin() }
                )
            },
            titleButton = "Login",
            onMainButtonClick = { navigateToHome() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(cvForm) {
                    top.linkTo(parent.top, margin = 160.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        SpannableText(
            modifier = Modifier.constrainAs(tvRegister) {
                bottom.linkTo(parent.bottom, margin = 24.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.clickable {
                navigateToRegister()
            },
            text1 = "Belum mempunyai akun? ",
            text2 = "Daftar"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginContentPreview() {
    JaddaTheme {
        LoginContent(
            navigateToRegister = {},
            navigateToHome = {},
            email = "",
            password = "",
            onValueChanged = {_, _ -> },
            doLogin = {},
        )
    }
}