package unsiq.mtqmn23.jadda.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import unsiq.mtqmn23.jadda.presentation.navigation.Screen
import unsiq.mtqmn23.jadda.presentation.screen.auth.login.LoginScreen
import unsiq.mtqmn23.jadda.presentation.screen.onboarding.OnBoardingScreen
import unsiq.mtqmn23.jadda.presentation.screen.splash.SplashScreen
import unsiq.mtqmn23.jadda.presentation.ui.theme.JaddaTheme

@OptIn(ExperimentalMaterial3Api::class)
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
                    Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) { contentPadding ->
                        NavHost(
                            modifier = Modifier.padding(contentPadding),
                            navController = navController,
                            startDestination = Screen.Splash.route,
                        ) {
                            composable(Screen.Splash.route) {
                                SplashScreen(
                                    onTimeOut = {
                                        navController.navigate(Screen.Login.route) {
                                            popUpTo(Screen.Splash.route) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                )
                            }
                            composable(Screen.OnBoarding.route) {
                                OnBoardingScreen(
                                    navigateToLogin = {

                                    }
                                )
                            }
                            composable(Screen.Login.route) {
                                LoginScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}