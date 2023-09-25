package unsiq.mtqmn23.jadda.presentation.screen.compass

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import unsiq.mtqmn23.jadda.presentation.components.RoundedButton
import unsiq.mtqmn23.jadda.presentation.screen.compass.components.Compass
import unsiq.mtqmn23.jadda.presentation.ui.theme.BlueSky
import unsiq.mtqmn23.jadda.presentation.ui.theme.JaddaTheme

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CompassScreen(
    navigateUp: () -> Unit,
    viewModel: CompassViewModel = hiltViewModel()
) {
    val permissionState = rememberMultiplePermissionsState(
        listOf(
            ACCESS_COARSE_LOCATION,
            ACCESS_FINE_LOCATION
        )
    )
    LaunchedEffect(Unit) {
        permissionState.launchMultiplePermissionRequest()
    }
    DisposableEffect(Unit) {
        viewModel.onEvent(CompassEvent.OnStartStopObserving(true))
        onDispose {
            viewModel.onEvent(CompassEvent.OnStartStopObserving(false))
        }
    }
    val state by viewModel.state.collectAsStateWithLifecycle()

    CompassContent(
        addressCity = state.addressCity,
        rotation = state.rotation,
        direction = state.destinationStatus?.direction,
        distance = state.destinationStatus?.distance,
        navigateUp = navigateUp
    )
}

@Composable
fun CompassContent(
    addressCity: String,
    direction: Int?,
    distance: Float?,
    rotation: Int,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            RoundedButton(
                icon = Icons.Default.ArrowBack,
                onClick = navigateUp,
                modifier = Modifier.padding(16.dp)
                    .align(Alignment.CenterStart)
            )
            Text(
                text = "Arah Kiblat",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = BlueSky,
                    shape = RoundedCornerShape(24.dp)
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.TopCenter)
                    .padding(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.MyLocation,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(addressCity)
            }
            Compass(
                direction = direction,
                rotation = rotation,
                modifier = Modifier.align(Alignment.Center)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.BottomCenter)
                    .padding(24.dp)
            ) {
                Text("Arah",)
                Text(
                    "${direction?.toString()}\u00B0" ?: "",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview(showBackground = false, device = Devices.PIXEL_4_XL)
@Composable
fun CompassContentPreview() {
    JaddaTheme {
        CompassContent(
            direction = 2,
            rotation = 0,
            distance = 3f,
            navigateUp = {},
            addressCity = ""
        )
    }
}