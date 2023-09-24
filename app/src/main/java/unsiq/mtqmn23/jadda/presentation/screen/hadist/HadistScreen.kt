package unsiq.mtqmn23.jadda.presentation.screen.hadist

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import unsiq.mtqmn23.jadda.domain.model.hadist.HadistItem
import unsiq.mtqmn23.jadda.presentation.ui.theme.Gray
import unsiq.mtqmn23.jadda.presentation.ui.theme.Green

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HadistScreen(
    snackbarHostState: SnackbarHostState,
    navigateToBack: () -> Unit,
    viewModel: HadistViewModel = hiltViewModel(),
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
                        text = "Hadist",
                        color = Color.Black,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),
                        textAlign = TextAlign.Center,
                    )
                },
                modifier = Modifier,
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
            HadistContent(
                name = state.dataHadist.name.toString(),
                requested = state.dataHadist.requested.toString(),
                hadist = state.dataHadist.hadist,
            )
        }
    }
}

@Composable
fun HadistContent(
    name: String,
    requested: String,
    hadist: List<HadistItem>,
) {
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
                text = name,
                color = Color.White,
                fontWeight = FontWeight.Medium,
            )
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Jumlah $requested",
                color = Color.White,
                textAlign = TextAlign.Right,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    LazyColumn {
        itemsIndexed(items = hadist, key = { _, item -> item.number ?: 0 }) { index, item ->
            Hadist(
                item.number ?: 0,
                item.textArab.toString(),
                item.translation.toString(),
            )
        }
    }
}

@Composable
fun Hadist(
    number: Int,
    textArab: String,
    translation: String,
) {
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
                    text = number.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Text(
                text = textArab,
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
        Divider(color = Gray.copy(alpha = 0.5f), thickness = 1.dp)
    }
}