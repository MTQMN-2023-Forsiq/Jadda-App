package unsiq.mtqmn23.jadda.presentation.screen.leaderboard

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
fun LeaderboardSecreen(
    navigateToBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Rangking",
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
            LeaderboardContent()
        }
    }
}

@Composable
fun LeaderboardContent() {
    Spacer(modifier = Modifier.height(60.dp))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val items = (1..10).toList()
        LazyColumn(
            modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp)
        ) {
            items(items.size) { item ->
                RankBox(
                    image = R.drawable.ic_avatar_sample,
                    name = "Febi Arifin",
                    point = 1000,
                    rank = item + 1,
                )
            }
        }
    }
}

@Composable
fun RankBox(
    image: Int,
    name: String,
    point: Int,
    rank: Int,
) {
    Spacer(modifier = Modifier.height(12.dp))
    Card (
        modifier = Modifier
            .shadow(
                elevation = 8.dp,
                ambientColor = Gray,
                spotColor = Green
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (rank == 1 || rank == 2 || rank == 3) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.ic_surah_number),
                        contentDescription = null,
                        modifier = Modifier
                            .height(35.dp)
                            .width(35.dp)
                            .align(Alignment.Center)
                    )
                    Text(
                        text = rank.toString(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                Text(
                    text = rank.toString(),
                    fontSize = 20.sp,
                    color = Green,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.width(30.dp),
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(image),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(2.dp, Gray, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = point.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                color = Green
            )
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
}

@Preview
@Composable
fun LeaderboardScreenPreview() {
    JaddaTheme {
        LeaderboardSecreen {

        }
    }
}