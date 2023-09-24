package unsiq.mtqmn23.jadda.presentation.screen.tajweed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import unsiq.mtqmn23.jadda.domain.model.tajweed.TajweedContentItem
import unsiq.mtqmn23.jadda.presentation.components.RoundedButton
import unsiq.mtqmn23.jadda.presentation.screen.quran.detail.MyAudioPlayer

@Composable
fun TajweedScreen(
    id: String,
    snackbarHostState: SnackbarHostState,
    navigateUp: () -> Unit,
    viewModel: TajweedViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewModel.onEvent(TajweedEvent.OnInitial(id))
    }

    state.statusMessage?.let {
        LaunchedEffect(null) {
            snackbarHostState.showSnackbar(it)
        }
    }

    if (state.isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        TajweedContent(
            detailTajweed = state.tajweed,
            navigateUp = navigateUp
        )
    }
}

@Composable
fun TajweedContent(
    detailTajweed: TajweedContentItem,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            RoundedButton(
                icon = Icons.Default.ArrowBack,
                onClick = navigateUp,
                modifier = Modifier.padding(16.dp)
                    .align(Alignment.CenterStart)
            )
            Text(
                text = "Detail Tajwid",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                "${detailTajweed.name}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(16.dp))
            Text(
                "${detailTajweed.description}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    lineHeight = 32.sp
                ),
                textAlign = TextAlign.Justify
            )
            Spacer(Modifier.height(16.dp))
            Text("Huruf hukum bacaan :", style = MaterialTheme.typography.bodyMedium)
            AsyncImage(
                model = detailTajweed.tajweedLetterUrl,
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(64.dp)
                    .align(Alignment.End)
            )
            Text("Contoh bacaan :", style = MaterialTheme.typography.bodyMedium)
            AsyncImage(
                model = detailTajweed.exampleUrl,
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(64.dp)
                    .align(Alignment.End)
            )
            Spacer(Modifier.height(16.dp))
            detailTajweed.audioUrl?.let {
                MyAudioPlayer(
                    audio = it
                )
            }
        }
    }
}