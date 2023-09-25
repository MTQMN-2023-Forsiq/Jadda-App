package unsiq.mtqmn23.jadda.presentation.screen.quran.detail

import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import unsiq.mtqmn23.jadda.R
import unsiq.mtqmn23.jadda.domain.model.quran.VersesItem
import unsiq.mtqmn23.jadda.presentation.ui.theme.Gray
import unsiq.mtqmn23.jadda.presentation.ui.theme.Green

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSurahScreen(
    id: String,
    snackbarHostState: SnackbarHostState,
    navigateToBack: () -> Unit,
    viewModel: DetailSurahViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewModel.onEvent(DetailSurahEvent.OnInitial(id))
    }

    state.statusMessage?.let {
        LaunchedEffect(it) {
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
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = state.dataSurah.shortName.toString(),
                            color = Color.Black,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),
                            textAlign = TextAlign.Center,
                        )
                    },
                    modifier = Modifier,
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(
                            onClick = { navigateToBack() }
                        ) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            },
        ) {
            Column {
                SurahContent(
                    title = state.dataSurah.name.toString(),
                    ayatCount = state.dataSurah.ayat ?: 0,
                    revelation = state.dataSurah.revelation.toString(),
                    verses = state.dataSurah.verses
                )
            }
        }
    }
}

@Composable
fun SurahContent(
    title: String,
    ayatCount: Int,
    revelation: String,
    verses: List<VersesItem>
) {
    Spacer(modifier = Modifier.height(64.dp))
    Row(
        modifier = Modifier
            .background(Green)
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                color = Color.White,
                fontWeight = FontWeight.Medium,
            )
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "$ayatCount Ayat, $revelation",
                color = Color.White,
                textAlign = TextAlign.Right,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    LazyColumn {
        items(items = verses, key = { it.number ?: 0 }) {
            Ayat(
                number = it.number ?: 0,
                arab = it.textArab.toString(),
                translation = it.translation.toString(),
                audio = it.audio.toString()
            )
        }
    }
}

@Composable
fun Ayat(
    number: Int,
    arab: String,
    translation: String,
    audio: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(14.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
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
                    text = number.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Text(
                text = arab,
                fontSize = 28.sp,
                fontFamily = FontFamily(Font(R.font.arabic_regular)),
                lineHeight = 40.sp,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
                    .weight(1f),
                textAlign = TextAlign.Right,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = translation,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 12.sp,
        )
        Spacer(modifier = Modifier.height(4.dp))
        MyAudioPlayer(audio = audio)
        Spacer(Modifier.height(8.dp))
        Divider(color = Gray.copy(alpha = 0.5f), thickness = 1.dp)
    }
}

@Composable
fun MyAudioPlayer(audio: String, modifier: Modifier = Modifier) {
    val ctx = LocalContext.current
    val mediaPlayer = remember { MediaPlayer() }
    var isPlaying by remember { mutableStateOf(false) }
    var isAudioComplete by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }

    Row(
        modifier = modifier,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier,
            onClick = {
                if (!isPlaying) {
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
                    try {
                        mediaPlayer.setDataSource(audio)
                        mediaPlayer.prepare()
                        mediaPlayer.start()
                        isPlaying = true
                        isAudioComplete = false
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                    if (!isAudioComplete) {
                        mediaPlayer.pause()
                        isPlaying = false
                    } else {
                        mediaPlayer.seekTo(0)
                        mediaPlayer.start()
                        isPlaying = true
                        isAudioComplete = false
                    }
                }
            }
        ) {
            Icon(
                imageVector = if (isPlaying && !isAudioComplete) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }
    }

    DisposableEffect(mediaPlayer) {
        onDispose {
            mediaPlayer.setOnCompletionListener(null)
        }
    }

    DisposableEffect(mediaPlayer) {
        val listener = MediaPlayer.OnCompletionListener {
            isPlaying = false
            isAudioComplete = true
            mediaPlayer.reset()
        }
        mediaPlayer.setOnCompletionListener(listener)
        onDispose {
            mediaPlayer.setOnCompletionListener(null)
        }
    }
}