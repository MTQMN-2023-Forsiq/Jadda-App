package unsiq.mtqmn23.jadda.presentation

import QuranScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.get
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import unsiq.mtqmn23.jadda.R
import unsiq.mtqmn23.jadda.presentation.components.NavigationBarItemJadda
import unsiq.mtqmn23.jadda.presentation.components.NavigationBarJadda
import unsiq.mtqmn23.jadda.presentation.navigation.NavigationItem
import unsiq.mtqmn23.jadda.presentation.navigation.Screen
import unsiq.mtqmn23.jadda.presentation.screen.auth.login.LoginScreen
import unsiq.mtqmn23.jadda.presentation.screen.auth.register.RegisterScreen
import unsiq.mtqmn23.jadda.presentation.screen.hadist.HadistScreen
import unsiq.mtqmn23.jadda.presentation.screen.home.HomeScreen
import unsiq.mtqmn23.jadda.presentation.screen.onboarding.OnBoardingScreen
import unsiq.mtqmn23.jadda.presentation.screen.profile.ProfileScreen
import unsiq.mtqmn23.jadda.presentation.screen.quran.detail.DetailSurahScreen
import unsiq.mtqmn23.jadda.presentation.screen.salat.SalatScreen
import unsiq.mtqmn23.jadda.presentation.screen.salatpractice.SalatPracticeScreen
import unsiq.mtqmn23.jadda.presentation.screen.splash.SplashScreen
import unsiq.mtqmn23.jadda.presentation.screen.tajweed.TajweedScreen
import unsiq.mtqmn23.jadda.presentation.screen.tajweeddetect.TajweedDetectScreen
import unsiq.mtqmn23.jadda.presentation.ui.theme.JaddaTheme
import unsiq.mtqmn23.jadda.util.sharedViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JaddaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val snackbarHostState = remember { SnackbarHostState() }
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route
                    Scaffold(
                        bottomBar = {
                            if (currentRoute.shouldShowBottomBar()) {
                                BottomBar(
                                    navController = navController
                                )
                            }
                        },
                        snackbarHost = { SnackbarHost(snackbarHostState) },
                        modifier = Modifier.fillMaxSize()
                    ) { contentPadding ->
                        NavHost(
                            modifier = Modifier.padding(contentPadding),
                            navController = navController,
                            startDestination = Screen.Splash.route,
                        ) {
                            composable(Screen.Splash.route) {
                                SplashScreen(
                                    onTimeOut = { isLoggedIn ->
                                        if (isLoggedIn) {
                                            navController.navigate(Screen.Home.route) {
                                                popUpTo(Screen.Splash.route) {
                                                    inclusive = true
                                                }
                                            }
                                        } else {
                                            navController.navigate("auth-graph") {
                                                popUpTo(Screen.Splash.route) {
                                                    inclusive = true
                                                }
                                            }
                                        }
                                    }
                                )
                            }
                            composable(Screen.Home.route) {
                                HomeScreen(
                                    snackbarHostState = snackbarHostState,
                                    navigateToTajweedDetection = {
                                        navController.navigate(Screen.TajweedDetect.route)
                                    },
                                    navigateToPracticeSalat = {
                                        navController.navigate("salat-graph")
                                    },
                                    navigateToDetailTajweed = {
                                        navController.navigate(Screen.Tajweed.createRoute(it))
                                    },
                                    navigateToHadist = {
                                        navController.navigate(Screen.Hadist.route)
                                    }
                                )
                            }
                            composable(Screen.Watch.route) {

                            }
                            composable(Screen.Quran.route) {
                                QuranScreen(
                                    snackbarHostState = snackbarHostState,
                                    navigateToDetail = {
                                        navController.navigate(Screen.QuranDetail.createRoute(it.toString()))
                                    }
                                )
                            }
                            composable(Screen.Hadist.route){
                                HadistScreen(
                                    snackbarHostState = snackbarHostState,
                                    navigateToBack = {
                                        navController.navigate(Screen.Home.route)
                                    }
                                )
                            }
                            composable(
                                Screen.QuranDetail.route,
                                arguments = listOf(
                                    navArgument("id") { type = NavType.StringType }
                                )
                            ) {
                                val id = it.arguments?.getString("id") ?: ""
                                DetailSurahScreen(
                                    id = id,
                                    snackbarHostState = snackbarHostState,
                                    navigateToBack = {
                                        navController.navigateUp()
                                    }
                                )
                            }
                            composable(Screen.Profile.route) {
                                ProfileScreen(
                                    navigateToBack = {}
                                )
                            }
                            composable(Screen.TajweedDetect.route) {
                                TajweedDetectScreen(
                                    navigateUp = {
                                        navController.navigateUp()
                                    },
                                    snackbarHostState = snackbarHostState
                                )
                            }
                            composable(
                                route = Screen.Tajweed.route,
                                arguments = listOf(
                                    navArgument("id") { type = NavType.StringType }
                                )
                            ) {
                                val id = it.arguments?.getString("id") ?: "0"
                                TajweedScreen(
                                    id = id,
                                    snackbarHostState = snackbarHostState,
                                    navigateUp = {
                                        navController.navigateUp()
                                    }
                                )
                            }
                            navigation(
                                startDestination = Screen.OnBoarding.route,
                                route = "auth-graph"
                            ) {
                                composable(Screen.OnBoarding.route) {
                                    OnBoardingScreen(
                                        navigateToLogin = {
                                            navController.navigate(Screen.Login.route)
                                        }
                                    )
                                }
                                composable(Screen.Login.route) {
                                    LoginScreen(
                                        snackBarHostState = snackbarHostState,
                                        navigateToRegister = {
                                            navController.navigate(Screen.Register.route)
                                        },
                                        navigateToHome = {
                                            navController.navigate(Screen.Home.route) {
                                                popUpTo("auth-graph") {
                                                    inclusive = true
                                                }
                                            }
                                        }
                                    )
                                }
                                composable(Screen.Register.route) {
                                    RegisterScreen(
                                        navigateUp = {
                                            navController.navigateUp()
                                        },
                                        snackbarHostState = snackbarHostState
                                    )
                                }
                            }
                            navigation(
                                startDestination = Screen.Salat.route,
                                route = "salat-graph"
                            ) {
                                composable(Screen.Salat.route) {
                                    val viewModel = it.sharedViewModel<MainViewModel>(navController)

                                    SalatScreen(
                                        navigateUp = {
                                            navController.navigateUp()
                                        },
                                        navigateToPractice = { items ->
                                            viewModel.setData(items)
                                            navController.navigate(Screen.SalatPractice.route)
                                        }
                                    )
                                }
                                composable(Screen.SalatPractice.route) {
                                    val viewModel = it.sharedViewModel<MainViewModel>(navController)
                                    val items by viewModel.salatItems.collectAsStateWithLifecycle()
                                    if (items.isNotEmpty()) {
                                        SalatPracticeScreen(
                                            salatItems = items,
                                            onNavigateUp = {
                                                navController.navigateUp()
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val items = listOf(
        NavigationItem(
            title = "Home",
            screen = Screen.Home,
            activeIcon = ImageVector.vectorResource(R.drawable.ic_home_active),
            inactiveIcon = ImageVector.vectorResource(R.drawable.ic_home),
        ),
        NavigationItem(
            title = "Tonton",
            screen = Screen.Watch,
            activeIcon = ImageVector.vectorResource(R.drawable.ic_watch_active),
            inactiveIcon = ImageVector.vectorResource(R.drawable.ic_watch),
        ),
        NavigationItem(
            title = "Al-Qur'an",
            screen = Screen.Quran,
            activeIcon = ImageVector.vectorResource(R.drawable.ic_quran_active),
            inactiveIcon = ImageVector.vectorResource(R.drawable.ic_quran),
        ),
        NavigationItem(
            title = "Profil",
            screen = Screen.Profile,
            activeIcon = ImageVector.vectorResource(R.drawable.ic_profile_active),
            inactiveIcon = ImageVector.vectorResource(R.drawable.ic_profile),
        ),
    )

    NavigationBarJadda(
        modifier = modifier,
        darkBackground = currentRoute == Screen.Watch.route
    ) {
        items.map { item ->
            NavigationBarItemJadda(
                selected = currentRoute == item.screen.route,
                label = item.title,
                icon = if (currentRoute == item.screen.route) item.activeIcon else item.inactiveIcon,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph[Screen.Home.route].id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

private fun String?.shouldShowBottomBar(): Boolean {
    return this in setOf(
        Screen.Home.route,
        Screen.Watch.route,
        Screen.Quran.route,
        Screen.Profile.route,
    )
}