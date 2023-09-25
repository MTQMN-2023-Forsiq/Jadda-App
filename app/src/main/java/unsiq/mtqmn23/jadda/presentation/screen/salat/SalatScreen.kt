package unsiq.mtqmn23.jadda.presentation.screen.salat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import unsiq.mtqmn23.jadda.R
import unsiq.mtqmn23.jadda.domain.model.salat.DataSalatItem
import unsiq.mtqmn23.jadda.presentation.components.RoundedButton
import unsiq.mtqmn23.jadda.presentation.screen.salat.components.ItemSalat
import unsiq.mtqmn23.jadda.presentation.ui.theme.BlueSky

@Composable
fun SalatScreen(
    navigateUp: () -> Unit,
    navigateToPractice: (List<DataSalatItem>) -> Unit,
    viewModel: SalatViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SalatContent(
        listSalat = state.lisSalat,
        navigateUp = navigateUp,
        navigateToPractice = navigateToPractice
    )
}

@Composable
fun SalatContent(
    listSalat: List<DataSalatItem>,
    navigateUp: () -> Unit,
    navigateToPractice: (List<DataSalatItem>) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RoundedButton(
                        icon = Icons.Default.ArrowBack,
                        onClick = navigateUp,
                        modifier = Modifier.padding(16.dp)
                            .align(Alignment.CenterStart)
                    )
                    Text(
                        text = "Praktik Sholat",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            items(items = listSalat) {
                ItemSalat(
                    title = it.title.toString(),
                    description = it.description.toString(),
                    imageUrl = it.imageUrl.toString(),
                    onClick = {
                    }
                )
            }
        }
        ExtendedFloatingActionButton(
            onClick = {
                navigateToPractice(listSalat)
            },
            containerColor = BlueSky,
            text = {
                Text("Praktik Sholat")
            },
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_salat),
                    contentDescription = null,
                )
            },
            modifier = Modifier.align(Alignment.BottomEnd)
                .padding(24.dp)
        )
    }
}