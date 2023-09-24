package unsiq.mtqmn23.jadda.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object OnBoarding : Screen("onboarding")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Quran : Screen("quran")
    object QuranDetail : Screen("quran-detail/{id}") {
        fun createRoute(id: String) = "quran-detail/$id"
    }
    object Watch : Screen("watch")
    object Tajweed : Screen("tajweed/{id}") {
        fun createRoute(id: String) = "tajweed/$id}"
    }
    object TajweedDetect : Screen("tajweed-detect")
    object Salat : Screen("salat")
    object SalatPractice : Screen("salat-practice")
}