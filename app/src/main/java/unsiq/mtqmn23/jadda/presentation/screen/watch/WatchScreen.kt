package unsiq.mtqmn23.jadda.presentation.screen.watch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.exoplayer.ExoPlayer
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import unsiq.mtqmn23.jadda.R
import unsiq.mtqmn23.jadda.domain.model.watch.WatchDataItem
import unsiq.mtqmn23.jadda.presentation.screen.watch.components.VideoPlayer

@Composable
fun WatchScreen(
    snackbarHostState: SnackbarHostState,
    viewModel: WatchViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    state.statusMessage?.let {
        LaunchedEffect(it) {
            snackbarHostState.showSnackbar(it)
        }
    }

    WatchContent(
        videos = state.videoItems,
        isLoading = state.isLoading
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WatchContent(
    videos: List<WatchDataItem>,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(videos.size)

    ConstraintLayout(
        modifier = modifier.fillMaxSize()
            .background(Color.Black)
    ) {
        val (topBar, videoPager) = createRefs()

        if (isLoading) {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            VerticalPager(
                state = pagerState,
                modifier = Modifier.constrainAs(videoPager) {
                    top.linkTo(topBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            ) {
                VideoItem(
                    video = videos[it],
                    pagerState = pagerState,
                    pageIndex = it,
                    onSingleTap = {},
                    onDoubleTap = { _, _ -> },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Box(
            modifier = Modifier.fillMaxWidth()
                .background(Color.Black)
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(
                text = "Tonton",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.White
                ),
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun VideoItem(
    video: WatchDataItem,
    pagerState: PagerState,
    pageIndex: Int,
    onSingleTap: (ExoPlayer) -> Unit,
    onDoubleTap: (ExoPlayer, Offset) -> Unit,
    modifier: Modifier = Modifier,
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.apply {
            setStatusBarColor(color = Color.Black)
            setNavigationBarColor(color = Color.Black)
        }
    }

    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (videoPlayer, titleText, infoButton, shareButton, overlayBlack) = createRefs()

        VideoPlayer(
            video = video,
            pagerState = pagerState,
            pageIndex = pageIndex,
            onDoubleTap = onDoubleTap,
            onSingleTap = onSingleTap,
            modifier = Modifier.constrainAs(videoPlayer) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }
        )

        Box(
            modifier = Modifier.fillMaxWidth()
                .height(100.dp)
                .constrainAs(overlayBlack) {
                    bottom.linkTo(parent.bottom)
                }
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black,
                        )
                    )
                )
        )

        Text(
            video.title.toString(),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White,
            ),
            modifier = Modifier
                .constrainAs(titleText) {
                    bottom.linkTo(parent.bottom, 48.dp)
                    start.linkTo(parent.start, 16.dp)
                }.padding(end = 72.dp)
        )

        IconButton(
            onClick = {},
            modifier = Modifier.constrainAs(infoButton) {
                bottom.linkTo(parent.bottom, 48.dp)
                end.linkTo(parent.end, 16.dp)
            }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_info),
                contentDescription = "Info",
                tint = Color.White
            )
        }

        IconButton(
            onClick = {},
            modifier = Modifier.constrainAs(shareButton) {
                bottom.linkTo(infoButton.top, 16.dp)
                start.linkTo(infoButton.start)
                end.linkTo(infoButton.end)
            },
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_share),
                contentDescription = "Share",
                tint = Color.White
            )
        }
    }
}