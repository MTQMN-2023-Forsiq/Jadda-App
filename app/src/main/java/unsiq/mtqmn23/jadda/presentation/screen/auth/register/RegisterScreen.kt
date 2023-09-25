package unsiq.mtqmn23.jadda.presentation.screen.auth.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import unsiq.mtqmn23.jadda.presentation.screen.auth.components.BackgroundAuth
import unsiq.mtqmn23.jadda.presentation.screen.auth.components.CardAuth
import unsiq.mtqmn23.jadda.presentation.screen.auth.components.CustomTextField
import unsiq.mtqmn23.jadda.presentation.screen.auth.components.PasswordTextField
import unsiq.mtqmn23.jadda.presentation.screen.auth.components.SpannableText

@Composable
fun RegisterScreen(
    snackbarHostState: SnackbarHostState,
    navigateUp: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    state.statusMessage?.let {
        LaunchedEffect(null) {
            snackbarHostState.showSnackbar(it)
        }
    }

    if (state.success) {
        LaunchedEffect(Unit) {
            navigateUp()
        }
    }

    RegisterContent(
        navigateUp = navigateUp,
        name = state.name,
        password = state.password,
        email = state.email,
        confirmPassword = state.confirmationPassword,
        isLoading = state.isLoading,
        onValueChanged = { name, email, password, confirmPassword ->
            viewModel.onEvent(RegisterEvent.OnValueChanged(name, email, password, confirmPassword))
        },
        doRegister = {
            viewModel.onEvent(RegisterEvent.OnRegister(state.name, state.email, state.password, state.confirmationPassword))
        }
    )
}

@Composable
fun RegisterContent(
    name: String,
    email: String,
    password: String,
    confirmPassword: String,
    isLoading: Boolean,
    onValueChanged: (name: String, email: String, password: String, confirmPassword: String) -> Unit,
    modifier: Modifier = Modifier,
    doRegister: () -> Unit,
    navigateUp: () -> Unit,
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
            title = "Mendaftar Akun"
        )

        CardAuth(
            inputForm = {
                Spacer(Modifier.height(16.dp))
                CustomTextField(
                    text = name,
                    onValueChange = {
                        onValueChanged(it, email, password, confirmPassword)
                    },
                    placeHolder = "Nama",
                    icon = Icons.Outlined.Person,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                )
                CustomTextField(
                    text = email,
                    onValueChange = {
                        onValueChanged(name, it, password, confirmPassword)
                    },
                    placeHolder = "Email",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    icon = Icons.Outlined.Email,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                )
                PasswordTextField(
                    text = password,
                    onValueChange = {
                        onValueChanged(name, email, it, confirmPassword)
                    },
                    placeHolder = "Password",
                    icon = Icons.Outlined.Lock,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                )
                PasswordTextField(
                    text = confirmPassword,
                    onValueChange = {
                        onValueChanged(name, email, password, it)
                    },
                    placeHolder = "Konfirmasi Password",
                    icon = Icons.Outlined.Lock,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                )
            },
            titleButton = "Daftar",
            onMainButtonClick = doRegister,
            isLoading = isLoading,
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
                navigateUp()
            },
            text1 = "Sudah mempunyai akun? ",
            text2 = "Masuk"
        )
    }
}