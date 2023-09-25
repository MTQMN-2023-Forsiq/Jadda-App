package unsiq.mtqmn23.jadda.presentation.screen.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import unsiq.mtqmn23.jadda.domain.model.tajweed.TajweedContentItem
import unsiq.mtqmn23.jadda.presentation.ui.theme.BlueSky
import unsiq.mtqmn23.jadda.presentation.ui.theme.Gray

@Composable
fun ItemTajweed(
    title: String,
    icon: String,
    type: RowType,
    expanded: Boolean,
    contents: List<TajweedContentItem>,
    onClick: () -> Unit,
    onDetailTajweedClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = when (type) {
        RowType.TOP -> {
            RoundedCornerShape(24.dp, 24.dp)
        }
        RowType.BOTTOM -> {
            RoundedCornerShape(
                0.dp,
                0.dp,
                24.dp,
                24.dp)
        }
        RowType.SINGLE -> {
            RoundedCornerShape(
                24.dp,
                24.dp,
                24.dp,
                24.dp)
        }
        else -> {
            RoundedCornerShape(0.dp)
        }
    }
    Box(
        modifier = modifier
            .clip(shape)
            .clickable(onClick = onClick)
            .background(color = BlueSky)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = icon,
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .background(Color.White, CircleShape)
                        .clip(CircleShape)
                        .border(width = 0.5.dp, color = Gray.copy(alpha = 0.5f), CircleShape)
                        .size(48.dp)
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Outlined.ExpandMore,
                    contentDescription = null,
                    modifier = Modifier.rotate(if (expanded) 180f else 0f)
                )
            }
            AnimatedVisibility(
                visible = expanded
            ) {
                Column {
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White
                    )
                    contents.forEach {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable {
                                    onDetailTajweedClick(it.id.toString())
                                }
                                .padding(vertical = 12.dp, horizontal = 16.dp)
                                .fillMaxWidth()
                        ) {
                            AsyncImage(
                                model = it.tajweedLetterUrl,
                                contentDescription = null,
                                contentScale = ContentScale.Inside,
                                modifier = Modifier
                                    .background(Color.White, RoundedCornerShape(8.dp))
                                    .clip(RoundedCornerShape(8.dp))
                                    .border(width = 0.5.dp, color = Gray.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                                    .size(width = 48.dp, height = 32.dp)
                            )
                            Spacer(Modifier.width(16.dp))
                            Text(
                                it.name.toString(),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}

enum class RowType {
    TOP, MIDDLE, BOTTOM, SINGLE
}