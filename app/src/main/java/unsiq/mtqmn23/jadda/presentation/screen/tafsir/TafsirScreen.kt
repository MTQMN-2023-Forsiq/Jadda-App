package unsiq.mtqmn23.jadda.presentation.screen.tafsir

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import unsiq.mtqmn23.jadda.R
import unsiq.mtqmn23.jadda.presentation.ui.theme.JaddaTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TafsirScreen(
    navigateToBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Tafsir As-Salam",
                        color = Color.Black,
                        fontSize = 18.sp,
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
            TafsirContent()
        }
    }
}

@Composable
fun TafsirContent() {
    Spacer(modifier = Modifier.height(62.dp))
    val items = (1..5).toList()
    Column(
        modifier = Modifier.padding(6.dp, 0.dp, 6.dp, 0.dp)
    ) {
        LazyColumn {
            items(items.size) { item ->
                Tafsir()
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Tafsir() {
    Spacer(modifier = Modifier.height(8.dp))
    Box {
        val path = "https://qive.rumahdigitalit.com/assets/images/1-hd.jpg"
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
                text = "1",
                fontSize = 14.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Preview
@Composable
fun TafsirScreenPreview() {
    JaddaTheme {
        TafsirScreen {

        }
    }
}