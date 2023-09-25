package unsiq.mtqmn23.jadda.presentation.screen.auth.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardAuth(
    inputForm: @Composable ColumnScope.() -> Unit,
    titleButton: String,
    isLoading: Boolean,
    onMainButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        modifier = modifier
    ) {
        inputForm()
        ButtonWithLoading(
            onClick = onMainButtonClick,
            isLoading = isLoading,
            text = titleButton,
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(bottom = 32.dp, top = 8.dp)
                .height(48.dp)
                .width(128.dp)
        )
    }
}