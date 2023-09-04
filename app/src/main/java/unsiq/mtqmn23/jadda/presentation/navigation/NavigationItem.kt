package unsiq.mtqmn23.jadda.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val screen: Screen,
    val activeIcon: ImageVector,
    val inactiveIcon: ImageVector,
)