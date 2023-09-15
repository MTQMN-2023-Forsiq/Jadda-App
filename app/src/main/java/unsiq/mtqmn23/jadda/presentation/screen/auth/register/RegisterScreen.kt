package unsiq.mtqmn23.jadda.presentation.screen.auth.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import unsiq.mtqmn23.jadda.presentation.screen.auth.components.BackgroundAuth
import unsiq.mtqmn23.jadda.presentation.screen.auth.components.CardAuth
import unsiq.mtqmn23.jadda.presentation.screen.auth.components.SpannableText

@Composable
fun RegisterScreen(
    navigateUp: () -> Unit,
) {
    RegisterContent(
        navigateUp = navigateUp,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
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
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text("Nama")
                    },
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text("Email")
                    },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
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
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text("Konfirmasi Password")
                    },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                )
            },
            titleButton = "Daftar",
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