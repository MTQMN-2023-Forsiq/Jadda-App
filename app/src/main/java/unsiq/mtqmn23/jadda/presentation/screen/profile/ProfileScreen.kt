package unsiq.mtqmn23.jadda.presentation.screen.profile

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import unsiq.mtqmn23.jadda.R
import unsiq.mtqmn23.jadda.presentation.MainActivity
import unsiq.mtqmn23.jadda.presentation.ui.theme.Black
import unsiq.mtqmn23.jadda.presentation.ui.theme.Green

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    snackbarHostState: SnackbarHostState,
    navigateToLeaderboard: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val activity = LocalContext.current as? Activity
    val state by viewModel.state.collectAsStateWithLifecycle()
    val systemUiController = rememberSystemUiController()

    state.statusMessage?.let {
        LaunchedEffect(it) {
            snackbarHostState.showSnackbar(it)
        }
    }

    LaunchedEffect(state.isLogout) {
        if (state.isLogout) {
            activity?.run {
                startActivity(Intent(activity, MainActivity::class.java))
                finish()
            }
        }
    }

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
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
        ProfileContent(
            avatar = state.profile.avatar ?: "-",
            name = state.profile.name ?: "-",
            pointEarned = state.profile.point ?: 0,
            email = state.profile.email ?: "-",
            taskCompleted = state.profile.taskComplete ?: 0,
            ranking = state.profile.ranking ?: 0,
            onLogout = {
                viewModel.onEvent(ProfileEvent.OnShowHideAlert(true))
            },
            navigateToLeaderboard = navigateToLeaderboard,
        )
    }

    if (state.isAlertShown) {
        AlertDialog(
            onDismissRequest = {
                viewModel.onEvent(ProfileEvent.OnShowHideAlert(false))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.onEvent(ProfileEvent.OnLogoutConfirmed)
                    }
                ) {
                    Text("Ya")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        viewModel.onEvent(ProfileEvent.OnShowHideAlert(false))
                    }
                ) {
                    Text("Tidak")
                }
            },
            text = {
                Text("Apakah anda yakin untuk keluar?")
            }
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileContent(
    avatar: String,
    name: String,
    pointEarned: Int,
    email: String,
    taskCompleted: Int,
    ranking: Int,
    onLogout: () -> Unit,
    navigateToLeaderboard: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        GlideImage(
            model = avatar,
            contentDescription = "Image",
            modifier = Modifier
                .height(80.dp)
                .width(80.dp)
        ) {
            it.error(R.drawable.ic_avatar_lazy)
                .placeholder(R.drawable.ic_avatar_lazy)
                .load(avatar)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
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
                    text = "$pointEarned",
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
                    text = "$taskCompleted",
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
                .background(
                    color = Green,
                    shape = RoundedCornerShape(16.dp)
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
                    text = "${ranking}th (Good Job)",
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
                        text = "$ranking",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
    Column {
        IconTextButton(
            text = "Leaderboard",
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Leaderboard,
                    contentDescription = null,
                    tint = Black,
                )
            },
            onClick = navigateToLeaderboard
        )
        IconTextButton(
            text = "Tentang Kami",
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
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
            onClick = onLogout
        )
    }
}

@Composable
fun IconTextButton(
    text: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier.padding(20.dp, 16.dp, 8.dp, 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            icon()
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}