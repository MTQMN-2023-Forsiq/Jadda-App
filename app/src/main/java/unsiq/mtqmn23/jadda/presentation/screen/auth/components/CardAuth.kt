package unsiq.mtqmn23.jadda.presentation.screen.auth.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardAuth(
    inputForm: @Composable ColumnScope.() -> Unit,
    titleButton: String,
    onMainButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        modifier = modifier
    ) {
        inputForm()
        Button(
            onClick = onMainButtonClick,
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(bottom = 32.dp, top = 8.dp)
        ) {
            Text(titleButton)
        }
    }
}