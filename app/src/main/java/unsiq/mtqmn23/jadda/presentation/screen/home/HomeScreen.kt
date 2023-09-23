package unsiq.mtqmn23.jadda.presentation.screen.home

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import unsiq.mtqmn23.jadda.R
import unsiq.mtqmn23.jadda.domain.model.tajweed.TajweedDataItem
import unsiq.mtqmn23.jadda.presentation.components.RoundedButton
import unsiq.mtqmn23.jadda.presentation.screen.home.components.ItemFeature
import unsiq.mtqmn23.jadda.presentation.screen.home.components.ItemMainFeature
import unsiq.mtqmn23.jadda.presentation.screen.home.components.ItemTajweed
import unsiq.mtqmn23.jadda.presentation.screen.home.components.RowType
import unsiq.mtqmn23.jadda.presentation.ui.theme.BlueSky
import unsiq.mtqmn23.jadda.presentation.ui.theme.Green

@Composable
fun HomeScreen(
    snackbarHostState: SnackbarHostState,
    navigateToTajweedDetection: () -> Unit,
    navigateToPracticeSalat: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.apply {
            setStatusBarColor(color = Color.White)
            setNavigationBarColor(color = Color.White)
        }
    }

    HomeContent(
        navigateToTajweedDetection = navigateToTajweedDetection,
        listTajweed = state.listTajweed,
        expandableCardIds = state.expandableCardIds,
        navigateToPracticeSalat = navigateToPracticeSalat,
        onTajweedCardClick = {
            viewModel.onEvent(HomeEvent.OnTajweedCardClick(it))
        }
    )
}

@Composable
fun HomeContent(
    navigateToTajweedDetection: () -> Unit,
    navigateToPracticeSalat: () -> Unit,
    expandableCardIds: SnapshotStateList<Int>,
    listTajweed: SnapshotStateList<TajweedDataItem>,
    onTajweedCardClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            HomeAppBar(
                date = "Kamis, 14 September",
                dateHijri = "12 Rajab 1443 H"
            )
        }
        item {
            HomeCard(
                myLocationName = "Wonosobo",
                nearPrayName = "Dzuhur",
                nearPrayTime = "11:34",
                subuhTime = "04:21",
                dzuhurTime = "11:34",
                asharTime = "15:27",
                maghribTime = "17:56",
                isyaTime = "18:55",
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp),
            )
        }
        item {
            FeatureCard(
                navigateToTafsir = {},
                navigateToCompass = {},
                navigateToHadits = {},
                navigateToPracticeSalat = navigateToPracticeSalat,
                navigateToRanking = {},
                navigateToTajweedDetection = navigateToTajweedDetection,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(16.dp))
        }
        when (listTajweed.size) {
            1 -> listTajweed.first().let {
                item {
                    ItemTajweed(
                        title = it.title.toString(),
                        icon = it.icon ?: "",
                        type = RowType.SINGLE,
                        expanded = false,
                        onClick = {},
                        contents = it.contents,
                    )
                }
            }
            else -> {
                itemsIndexed(listTajweed) { index, item ->
                    val rowType = when (index) {
                        0 -> RowType.TOP
                        listTajweed.lastIndex -> RowType.BOTTOM
                        else -> RowType.MIDDLE
                    }
                    ItemTajweed(
                        title = item.title.toString(),
                        icon = item.icon ?: "",
                        contents = item.contents,
                        type = rowType,
                        onClick = { onTajweedCardClick(item.id ?: 0) },
                        expanded = expandableCardIds.contains(item.id),
                        modifier = Modifier
                            .padding(vertical = 0.dp, horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeAppBar(
    date: String,
    dateHijri: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = date,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(Modifier.height(2.dp))
            Text(dateHijri)
        }
        RoundedButton(
            icon = ImageVector.vectorResource(R.drawable.ic_bell),
            onClick = {}
        )
    }
}

@Composable
fun HomeCard(
    modifier: Modifier = Modifier,
    myLocationName: String?,
    nearPrayName: String?,
    nearPrayTime: String?,
    subuhTime: String?,
    dzuhurTime: String?,
    asharTime: String?,
    maghribTime: String?,
    isyaTime: String?,
) {
    Box(
        modifier = modifier
            .background(color = Green, shape = RoundedCornerShape(24.dp)),
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_mosque_dark),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.align(Alignment.BottomEnd)
                .height(224.dp)
                .clip(RoundedCornerShape(bottomEnd = 24.dp))
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.MyLocation,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(Modifier.width(8.dp))
                Text(myLocationName ?: "Aktifkan Lokasi", color = Color.White)
            }
            Spacer(Modifier.height(24.dp))
            Text(
                text = nearPrayName ?: "-",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            Text(
                text = nearPrayTime ?: "-",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White,
            )
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PrayScheduleItem(
                    title = "Subuh",
                    time = subuhTime,
                    icon = ImageVector.vectorResource(R.drawable.ic_subuh)
                )
                PrayScheduleItem(
                    title = "Dzuhur",
                    time = dzuhurTime,
                    icon = ImageVector.vectorResource(R.drawable.ic_dzuhur)
                )
                PrayScheduleItem(
                    title = "Ashar",
                    time = asharTime,
                    icon = ImageVector.vectorResource(R.drawable.ic_ashar)
                )
                PrayScheduleItem(
                    title = "Maghrib",
                    time = maghribTime,
                    icon = ImageVector.vectorResource(R.drawable.ic_maghrib)
                )
                PrayScheduleItem(
                    title = "Isya",
                    time = isyaTime,
                    icon = ImageVector.vectorResource(R.drawable.ic_isya)
                )
            }
        }
    }
}

@Composable
fun PrayScheduleItem(
    modifier: Modifier = Modifier,
    title: String,
    time: String?,
    icon: ImageVector,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = Color.White,
        )
        Icon(
            imageVector = icon,
            contentDescription = time,
            tint = Color.White,
        )
        Text(
            text = time ?: "-",
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = Color.White
        )
    }
}

@Composable
fun FeatureCard(
    modifier: Modifier = Modifier,
    navigateToTafsir: () -> Unit,
    navigateToCompass: () -> Unit,
    navigateToHadits: () -> Unit,
    navigateToRanking: () -> Unit,
    navigateToTajweedDetection: () -> Unit,
    navigateToPracticeSalat: () -> Unit,
) {
    Box(
        modifier = modifier
            .background(color = BlueSky, shape = RoundedCornerShape(24.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                ItemFeature(
                    onClick = navigateToTafsir,
                    title = "Tafsir",
                    icon = ImageVector.vectorResource(R.drawable.ic_tafsir),
                )
                ItemFeature(
                    onClick = navigateToCompass,
                    title = "Kiblat",
                    icon = ImageVector.vectorResource(R.drawable.ic_kiblah),
                )
                ItemFeature(
                    onClick = navigateToHadits,
                    title = "Hadits",
                    icon = ImageVector.vectorResource(R.drawable.ic_hadits),
                )
                ItemFeature(
                    onClick = navigateToRanking,
                    title = "Ranking",
                    icon = ImageVector.vectorResource(R.drawable.ic_cup),
                )
            }
            Spacer(Modifier.height(8.dp))
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White
            )
            Spacer(Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.width(16.dp))
                ItemMainFeature(
                    title = "Deteksi \nTajwid",
                    icon = ImageVector.vectorResource(R.drawable.ic_scan),
                    onClick = navigateToTajweedDetection,
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(24.dp))
                Divider(
                    modifier = Modifier
                        .size(1.dp, 56.dp),
                    color = Color.White
                )
                Spacer(Modifier.width(24.dp))
                ItemMainFeature(
                    title = "Praktik \nSholat",
                    icon = ImageVector.vectorResource(R.drawable.ic_salat),
                    onClick = navigateToPracticeSalat,
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(24.dp))
            }
        }
    }
}