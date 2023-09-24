package unsiq.mtqmn23.jadda.presentation.screen.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import unsiq.mtqmn23.jadda.R
import unsiq.mtqmn23.jadda.presentation.ui.theme.Black
import unsiq.mtqmn23.jadda.presentation.ui.theme.Green
import unsiq.mtqmn23.jadda.presentation.ui.theme.JaddaTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navigateToBack: () -> Unit,
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.apply {
            setStatusBarColor(color = Color.White)
            setNavigationBarColor(color = Color.White)
        }
    }

    Column {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Profil",
                style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        ProfileContent()
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileContent() {
    Spacer(modifier = Modifier.height(60.dp))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
    ) {
        val path = "https://qive.rumahdigitalit.com/assets/images/avatar.png"
        GlideImage(
            model = path,
            contentDescription = "Image",
            modifier = Modifier
                .height(64.dp)
                .width(64.dp)
        ){
            it
                .error(R.drawable.ic_avatar_lazy)
                .placeholder(R.drawable.ic_avatar_lazy)
                .load(path)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Sumpeno",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "1200",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Point Earned",
                    fontSize = 12.sp,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "20",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Task Completed",
                    fontSize = 12.sp,
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .padding(8.dp)
                .background(
                    color = Green,
                    shape = RoundedCornerShape(20.dp)
                )
                .fillMaxWidth()
                .height(80.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(20.dp, 0.dp, 0.dp, 0.dp)
            ) {
                Text(
                    text = "Your today rangking",
                    fontSize = 12.sp,
                    color = Color.White,
                )
                Text(
                    text = "5th (Good Job)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 36.dp, 0.dp)
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.ic_ranking_number),
                        contentDescription = null,
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                            .align(Alignment.Center)
                    )
                    Text(
                        text = "5",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
    Column{
        IconTextButton(
            text = "Leaderboard",
            icon = {
                Icon(
                    imageVector = Icons.Default.Leaderboard,
                    contentDescription = null,
                    tint = Black,
                )
            },
            onClick = {

            }
        )
        IconTextButton(
            text = "Tentang Kami",
            icon = {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = Black,
                )
            },
            onClick = {

            }
        )
        IconTextButton(
            text = "Logout",
            icon = {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = null,
                    tint = Black,
                )
            },
            onClick = {

            }
        )
    }
}

@Composable
fun IconTextButton(
    text: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick },
    ) {
        Row(
            modifier = Modifier.padding(20.dp,16.dp,8.dp,16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            icon()
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.button.copy(color = Black),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreiew() {
    JaddaTheme {
        ProfileScreen {

        }
    }
}