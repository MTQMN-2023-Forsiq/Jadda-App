package unsiq.mtqmn23.jadda.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import unsiq.mtqmn23.jadda.R
import unsiq.mtqmn23.jadda.presentation.ui.theme.BlueSky
import unsiq.mtqmn23.jadda.presentation.ui.theme.JaddaTheme

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .background(color = BlueSky, shape = RoundedCornerShape(35))
            .clip(RoundedCornerShape(35))
            .clickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.padding(16.dp)
                .size(24.dp)
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RoundedButtonPreview() {
    JaddaTheme {
        RoundedButton(
            icon = ImageVector.vectorResource(R.drawable.ic_bell),
            onClick = {}
        )
    }
}