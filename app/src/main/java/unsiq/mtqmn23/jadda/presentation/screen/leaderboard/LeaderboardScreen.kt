package unsiq.mtqmn23.jadda.presentation.screen.leaderboard

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import unsiq.mtqmn23.jadda.domain.model.ranking.RankingItem
import unsiq.mtqmn23.jadda.presentation.screen.leaderboard.components.ItemLeaderboard
import unsiq.mtqmn23.jadda.presentation.screen.leaderboard.components.ItemTopUserLeader

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LeaderboardScreen(
    navigateToBack: () -> Unit,
    viewModel: LeaderboardViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    if (state.isError) {
        LaunchedEffect(key1 = state.statusMessage) {
            Toast.makeText(context, state.statusMessage, Toast.LENGTH_SHORT).show()
        }
    }

    LeaderboardContent(
        navigateUp = navigateToBack,
        listLeaderboard = state.results
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardContent(
    listLeaderboard: List<RankingItem>,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Leaderboard") },
                navigationIcon = {
                    IconButton(
                        onClick = navigateUp
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Navigate Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { contentPadding ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier.padding(contentPadding)
        ) {
            item {
                TopUserLeaderboard(
                    firstPosition = listLeaderboard.firstOrNull { it.rangking == 1 } ?: RankingItem(),
                    secondPosition = listLeaderboard.firstOrNull { it.rangking == 2 } ?: RankingItem(),
                    thirdPosition = listLeaderboard.firstOrNull { it.rangking == 3 } ?: RankingItem(),
                )
                Spacer(Modifier.height(16.dp))
            }
            itemsIndexed(items = listLeaderboard.filter { it.rangking > 3 }, key = { _, item -> item.userId }) { index, item ->
                ItemLeaderboard(
                    name = item.name,
                    photoUrl = item.name,
                    points = item.point.toInt(),
                    position = index + 4
                )
            }
        }
    }
}

@Composable
fun TopUserLeaderboard(
    firstPosition: RankingItem,
    secondPosition: RankingItem,
    thirdPosition: RankingItem,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (firstUser, secondUser, thirdUser) = createRefs()

        ItemTopUserLeader(
            rankingPosition = 1,
            image = firstPosition.name,
            name = firstPosition.name,
            points = firstPosition.point.toInt(),
            modifier = Modifier
                .constrainAs(firstUser) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
        )
        ItemTopUserLeader(
            rankingPosition = 2,
            image = secondPosition.name,
            name = secondPosition.name,
            points = secondPosition.point.toInt(),
            modifier = Modifier
                .constrainAs(secondUser) {
                    top.linkTo(firstUser.top, margin = 72.dp)
                    end.linkTo(firstUser.start)
                    start.linkTo(parent.start)
                }
        )
        ItemTopUserLeader(
            rankingPosition = 3,
            image = thirdPosition.name,
            name = thirdPosition.name,
            points = thirdPosition.point.toInt(),
            modifier = Modifier
                .constrainAs(thirdUser) {
                    top.linkTo(firstUser.top, margin = 72.dp)
                    start.linkTo(firstUser.end)
                    end.linkTo(parent.end)
                }
        )
    }
}