package unsiq.mtqmn23.jadda.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object OnBoarding : Screen("onboarding")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
}