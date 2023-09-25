package unsiq.mtqmn23.jadda.presentation.screen.auth.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CustomTextField(
    text: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        placeholder = {
            Text(placeHolder)
        },
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}