package unsiq.mtqmn23.jadda.presentation.screen.tafsir

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import unsiq.mtqmn23.jadda.R
import unsiq.mtqmn23.jadda.domain.model.tafsir.TafsirDataItem
import unsiq.mtqmn23.jadda.presentation.components.RoundedButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TafsirScreen(
    snackbarHostState: SnackbarHostState,
    navigateToBack: () -> Unit,
    viewModel: TafsirViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    state.statusMessage?.let {
        LaunchedEffect(it) {
            snackbarHostState.showSnackbar(it)
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Tafsir As-Salam",
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White),
                navigationIcon = {
                    RoundedButton(
                        icon = Icons.Default.ArrowBack,
                        onClick = navigateToBack,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            )
        },
    ) {
        Column {
            TafsirContent(state.tafsir)
        }
    }
}

@Composable
fun TafsirContent(
    tafsir: List<TafsirDataItem>,
) {
    Spacer(modifier = Modifier.height(62.dp))
    Column(
        modifier = Modifier.padding(6.dp, 0.dp, 6.dp, 0.dp)
    ) {
        LazyColumn {
            itemsIndexed(items = tafsir, key = { _, item -> item.id ?: 0 }) { index, item ->
                Tafsir(
                    number = item.id.toString(),
                    image = item.image.toString(),
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Tafsir(
    number: String,
    image: String
) {
    Spacer(modifier = Modifier.height(8.dp))
    Box {
        val path = image
        GlideImage(
            model = path,
            contentDescription = "Image",
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            it
                .error(R.drawable.ic_lazy_tafsir)
                .placeholder(R.drawable.ic_lazy_tafsir)
                .load(path)
        }
        Box {
            Image(
                painter = painterResource(id = R.drawable.ic_surah_number),
                contentDescription = null,
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp)
                    .align(Alignment.Center)
            )
            Text(
                text = number,
                fontSize = 14.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}