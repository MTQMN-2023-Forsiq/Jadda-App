package unsiq.mtqmn23.jadda.presentation.screen.quran.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
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
    Row (
        modifier = Modifier
            .background(Green)
            .padding(6.dp)
            .fillMaxWidth()
    ){
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
    LazyColumn{
        items(items.size){item ->
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
        Divider(color = Gray.copy(alpha = 0.5f), thickness = 1.dp)
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