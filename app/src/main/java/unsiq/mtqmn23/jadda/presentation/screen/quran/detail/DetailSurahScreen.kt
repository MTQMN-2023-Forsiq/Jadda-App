package unsiq.mtqmn23.jadda.presentation.screen.quran.detail

import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import unsiq.mtqmn23.jadda.R
import unsiq.mtqmn23.jadda.presentation.ui.theme.Gray
import unsiq.mtqmn23.jadda.presentation.ui.theme.Green
import unsiq.mtqmn23.jadda.presentation.ui.theme.JaddaTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSurahScreen(
    navigateToBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "الفاتحة",
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),
                        textAlign = TextAlign.Center,
                    )
                },
                modifier = Modifier
                    .border(1.dp, color = Color.Gray.copy(alpha = 0.3f)),
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
            SurahContent()
        }
    }
}

@Composable
fun SurahContent() {
    Spacer(modifier = Modifier.height(64.dp))
    Row(
        modifier = Modifier
            .background(Green)
            .padding(6.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Al-Fatihah",
                color = Color.White,
                fontWeight = FontWeight.Medium,
            )
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "7 Ayat, Makiyyah",
                color = Color.White,
                textAlign = TextAlign.Right,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    val items = (1..7).toList()
    LazyColumn {
        items(items.size) { item ->
            Ayat()
        }
    }
}

@Composable
fun Ayat() {
    Column(
        modifier = Modifier.padding(14.dp)
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
                    text = "1",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Text(
                text = "بِسْمِ اللهِ الرَّحْمَنِ الرَّحِيْمِِ",
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
                    .weight(1f),
                textAlign = TextAlign.Right,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Dengan nama Allah yang maha pengasih lagi maha penyayang.",
            fontStyle = FontStyle.Italic,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 12.sp,
        )
        Spacer(modifier = Modifier.height(4.dp))
        MyAudioPlayer(audio = "https://cdn.islamic.network/quran/audio/64/ar.alafasy/1.mp3")
        Divider(color = Gray.copy(alpha = 0.5f), thickness = 1.dp)
    }
}

@Composable
fun MyAudioPlayer(audio: String) {
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
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .fillMaxSize()
            .padding(6.dp),
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .padding(6.dp),
            onClick = {
                if (!isPlaying) {
                    val audioUrl = audio
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
                    try {
                        mediaPlayer.setDataSource(audioUrl)
                        mediaPlayer.prepare()
                        mediaPlayer.start()
                        isPlaying = true
                        isAudioComplete = false
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    Toast.makeText(ctx, "Audio started playing..", Toast.LENGTH_SHORT).show()
                } else {
                    if (!isAudioComplete) {
                        mediaPlayer.pause()
                        isPlaying = false
                        Toast.makeText(ctx, "Audio paused..", Toast.LENGTH_SHORT).show()
                    } else {
                        mediaPlayer.seekTo(0)
                        mediaPlayer.start()
                        isPlaying = true
                        isAudioComplete = false
                        Toast.makeText(ctx, "Audio restarted..", Toast.LENGTH_SHORT).show()
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
        }
        mediaPlayer.setOnCompletionListener(listener)
        onDispose {
            mediaPlayer.setOnCompletionListener(null)
        }
    }
}


@Preview
@Composable
fun DetailSurahScreenPreview() {
    JaddaTheme {
        DetailSurahScreen(
            navigateToBack = {}
        )
    }
}