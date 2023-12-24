package unsiq.mtqmn23.jadda.presentation.screen.leaderboard.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import unsiq.mtqmn23.jadda.R

@Composable
fun ItemTopUserLeader(
    rankingPosition: Int,
    image: String?,
    name: String,
    points: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (rankingPosition == 1) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_crown),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.offset(y = 4.dp)
            )
        }
        AsyncImage(
            model = R.drawable.ic_avatar_sample,
            contentDescription = name,
            modifier = Modifier
                .size(if (rankingPosition == 1) 80.dp else 64.dp)
                .clip(CircleShape)
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = when (rankingPosition) {
                            1 -> MaterialTheme.colorScheme.primary
                            2 -> MaterialTheme.colorScheme.secondary
                            3 -> MaterialTheme.colorScheme.tertiary
                            else -> Color.Unspecified
                        }
                    ),
                    shape = CircleShape
                )
        )
        Text(
            text = "  $rankingPosition  ",
            style = MaterialTheme.typography.titleSmall.copy(
                color = Color.White
            ),
            modifier = Modifier
                .offset(y = (-12).dp)
                .background(
                    color = when (rankingPosition) {
                        1 -> MaterialTheme.colorScheme.primary
                        2 -> MaterialTheme.colorScheme.secondary
                        3 -> MaterialTheme.colorScheme.tertiary
                        else -> Color.Unspecified
                    },
                    shape = CircleShape
                )
        )
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "$points",
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary
            )
        )
    }
}

