package unsiq.mtqmn23.jadda.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object OnBoarding : Screen("onboarding")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Quran : Screen("quran")
    object Watch : Screen("watch")
    object TajweedDetect : Screen("tajweed-detect")
    object Salat : Screen("salat")
    object SalatPractice : Screen("salat-practice")
}