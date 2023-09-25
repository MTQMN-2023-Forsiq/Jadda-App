package unsiq.mtqmn23.jadda.presentation.screen.onboarding

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import unsiq.mtqmn23.jadda.presentation.screen.onboarding.model.OnBoardingData
import unsiq.mtqmn23.jadda.presentation.screen.onboarding.model.OnBoardingItem
import unsiq.mtqmn23.jadda.presentation.ui.theme.Black
import unsiq.mtqmn23.jadda.presentation.ui.theme.BlueSky
import unsiq.mtqmn23.jadda.presentation.ui.theme.Gray
import unsiq.mtqmn23.jadda.presentation.ui.theme.Green
import unsiq.mtqmn23.jadda.presentation.ui.theme.JaddaTheme

@ExperimentalPagerApi
@Composable
fun OnBoardingScreen(
    navigateToLogin: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val systemUiController = rememberSystemUiController()

    DisposableEffect(Unit) {
        systemUiController.apply {
            setStatusBarColor(color = Color.White)
            setNavigationBarColor(color = Color.White)
        }
        onDispose {
            systemUiController.apply {
                setStatusBarColor(color = Green)
                setNavigationBarColor(color = Green)
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopSection(onSkipClick = navigateToLogin)

        val items = OnBoardingData.onBoardingItems
        val state = rememberPagerState(pageCount = 3)
        HorizontalPager(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
        ) { page ->
            OnBoardingContent(items[page], navigateToLogin, size = items.size, index = state.currentPage){
                if (state.currentPage + 1 < items.size) {
                    scope.launch {
                        state.scrollToPage(state.currentPage + 1)
                    }
                }
            }
        }
    }
}

@Composable
fun TopSection(
    onSkipClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        //Skip button
        TextButton(
            onClick = onSkipClick,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Text("Skip", color = MaterialTheme.colorScheme.onBackground)
        }
    }
}

@Composable
fun OnBoardingContent(
    item: OnBoardingItem,
    navigateToLogin: () -> Unit,
    size: Int,
    index: Int,
    onNextClicked: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(id = item.image), contentDescription = null)
        Column(
            modifier = Modifier
                .padding(14.dp)
                .background(
                    color = BlueSky,
                    shape = RoundedCornerShape(20.dp)
                )
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Box{
                Indicators(size = size, index = index)
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = item.title,
                fontSize = 24.sp,
                color = Black,
            )
            Text(
                text = item.description,
                fontSize = 14.sp,
                color = Black,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .padding(20.dp)
                    .width(240.dp),
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = if (index == 2) navigateToLogin else onNextClicked,
                modifier = Modifier
                    .height(48.dp)
                    .width(128.dp)
            ) {
                Text(
                    text = if (index == 2) "Mulai" else "Lanjut",
                )
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
fun BoxScope.Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 24.dp else 8.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = ""
    )

    Box(
        modifier = Modifier
            .height(8.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) Black else Gray
            )
    ) {

    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun OnBoardingScreenPreview() {
    JaddaTheme {
        OnBoardingScreen {

        }
    }
}