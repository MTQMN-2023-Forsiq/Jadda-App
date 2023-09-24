package unsiq.mtqmn23.jadda.presentation.screen.tajweeddetect

import android.Manifest
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RichTooltipBox
import androidx.compose.material3.RichTooltipState
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.objectdetector.ObjectDetectionResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import unsiq.mtqmn23.jadda.domain.model.tajweed.TajweedContentItem
import unsiq.mtqmn23.jadda.presentation.components.RoundedButton
import unsiq.mtqmn23.jadda.presentation.screen.tajweeddetect.components.ResultsOverlay
import unsiq.mtqmn23.jadda.presentation.screen.tajweeddetect.objectdetector.ObjectDetectorHelper
import unsiq.mtqmn23.jadda.presentation.screen.tajweeddetect.objectdetector.ObjectDetectorListener
import unsiq.mtqmn23.jadda.presentation.ui.theme.Green
import unsiq.mtqmn23.jadda.presentation.ui.theme.Yellow
import unsiq.mtqmn23.jadda.util.getFittedBoxSize
import java.util.concurrent.Executors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TajweedDetectScreen(
    navigateUp: () -> Unit,
    snackbarHostState: SnackbarHostState,
    viewModel: TajweedDetectViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TajweedDetectContent(
        navigateUp = navigateUp,
        isSheetOpen = state.isSheetOpen,
        showDetailTajweed = { tajweedId ->
            viewModel.onEvent(TajweedDetectEvent.OnBoundsClick(tajweedId))
        },
        detailTajweed = state.detailTajweed,
        dismissSheet = {
            viewModel.onEvent(TajweedDetectEvent.OnDismissSheet)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TajweedDetectContent(
    navigateUp: () -> Unit,
    dismissSheet: () -> Unit,
    isSheetOpen: Boolean,
    detailTajweed: TajweedContentItem,
    showDetailTajweed: (String) -> Unit,
    modifier: Modifier = Modifier,
    bottomSheetState: SheetState = rememberModalBottomSheetState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {
    val tooltipState: RichTooltipState = remember { RichTooltipState() }
    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (topBar, cameraView, tooltip, infoView) = createRefs()
        Box(
            modifier = Modifier.fillMaxWidth()
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            RoundedButton(
                icon = Icons.Default.ArrowBack,
                onClick = navigateUp,
                modifier = Modifier.padding(16.dp)
                    .align(Alignment.CenterStart)
            )
            Text(
                text = "Deteksi Tajwid",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Center)
            )
            RoundedButton(
                icon = Icons.Outlined.Info,
                onClick = {
                    coroutineScope.launch {
                        tooltipState.show()
                    }
                },
                modifier = Modifier.padding(16.dp)
                    .align(Alignment.CenterEnd)
            )
        }

        CameraView(
            setInferenceTime = {},
            showDetailTajweed = showDetailTajweed,
            modifier = Modifier.constrainAs(cameraView) {
                top.linkTo(topBar.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        RichTooltipBox(
            text = {
                Text("Arahkan kamera ke kitab suci Al-Qur'an. Jika terdeteksi suatu hukum bacaan, pengguna bisa mengklik pada kotak deteksi untuk melihat penjelasan lengkap hukum bacaan tajwid.")
            },
            title = {
                Text("Pendeteksi Hukum Tajwid")
            },
            tooltipState = tooltipState,
            action = {
                TextButton(
                    onClick = {
                        coroutineScope.launch {
                            tooltipState.dismiss()
                        }
                    }
                ) { Text("Ok") }
            },
            content = {},
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
                .constrainAs(tooltip) {
                    top.linkTo(topBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
                .background(Color.White)
                .constrainAs(infoView) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.size(24.dp).background(Green, RoundedCornerShape(8.dp)))
                Spacer(Modifier.width(8.dp))
                Text(
                    "Mad Iwadl",
                    color = Green
                )
            }
            Spacer(Modifier.width(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.size(24.dp).background(Yellow, RoundedCornerShape(8.dp)))
                Spacer(Modifier.width(8.dp))
                Text(
                    "Ghunnah",
                    color = Yellow
                )
            }
        }
    }

    if (isSheetOpen) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = dismissSheet,
            containerColor = Color.White
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(
                    "${detailTajweed.name}",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    "${detailTajweed.description}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        lineHeight = 32.sp
                    ),
                    textAlign = TextAlign.Justify
                )
                Spacer(Modifier.height(16.dp))
                Text("Huruf hukum bacaan :", style = MaterialTheme.typography.bodyMedium)
                AsyncImage(
                    model = detailTajweed.tajweedLetterUrl,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .height(64.dp)
                        .align(Alignment.End)
                )
                Text("Contoh bacaan :", style = MaterialTheme.typography.bodyMedium)
                AsyncImage(
                    model = detailTajweed.exampleUrl,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .height(64.dp)
                        .align(Alignment.End)
                )
                Spacer(Modifier.height(16.dp))
                detailTajweed.audioUrl?.let {
                    AudioPlayer(
                        audioUrl = it
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraView(
    setInferenceTime: (newInferenceTime: Int) -> Unit,
    showDetailTajweed: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val storagePermissionState: PermissionState =
        rememberPermissionState(Manifest.permission.CAMERA)

    LaunchedEffect(key1 = Unit) {
        if (!storagePermissionState.hasPermission) {
            storagePermissionState.launchPermissionRequest()
        }
    }

    if (!storagePermissionState.hasPermission) {
        Text(text = "No Storage Permission!")
        return
    }

    var results by remember {
        mutableStateOf<ObjectDetectionResult?>(null)
    }

    var frameHeight by remember {
        mutableStateOf(16)
    }

    var frameWidth by remember {
        mutableStateOf(9)
    }

    var active by remember {
        mutableStateOf(true)
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

    DisposableEffect(Unit) {
        onDispose {
            active = false;
            cameraProviderFuture.get().unbindAll()
        }
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        val cameraPreviewSize = getFittedBoxSize(
            containerSize = Size(
                width = this.maxWidth.value,
                height = this.maxHeight.value,
            ),
            boxSize = Size(
                width = frameWidth.toFloat(),
                height = frameHeight.toFloat()
            )
        )

        Box(
            Modifier
                .width(cameraPreviewSize.width.dp)
                .height(cameraPreviewSize.height.dp),
        ) {
            AndroidView(
                factory = { ctx ->
                    val previewView = PreviewView(ctx)
                    val executor = ContextCompat.getMainExecutor(ctx)
                    cameraProviderFuture.addListener({
                        val cameraProvider = cameraProviderFuture.get()

                        val preview = Preview.Builder().build().also {
                            it.setSurfaceProvider(previewView.surfaceProvider)
                        }

                        val cameraSelector = CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build()

                        val imageAnalyzer =
                            ImageAnalysis.Builder()
                                .setTargetAspectRatio(AspectRatio.RATIO_16_9)
                                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                                .build()

                        val backgroundExecutor = Executors.newSingleThreadExecutor()

                        backgroundExecutor.execute {
                            val objectDetectorHelper =
                                ObjectDetectorHelper(
                                    context = ctx,
                                    objectDetectorListener = ObjectDetectorListener(
                                        onErrorCallback = { a, b ->
                                        },
                                        onResultsCallback = {
                                            frameHeight = it.inputImageHeight
                                            frameWidth = it.inputImageWidth

                                            if (active) {
                                                results = it.results.first()
                                                setInferenceTime(it.inferenceTime.toInt())
                                            }
                                        }
                                    ),
                                    runningMode = RunningMode.LIVE_STREAM
                                )

                            imageAnalyzer.setAnalyzer(
                                backgroundExecutor,
                                objectDetectorHelper::detectLivestreamFrame
                            )
                        }
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            imageAnalyzer,
                            preview
                        )
                    }, executor)
                    previewView
                },
                modifier = Modifier.fillMaxSize(),
            )
            results?.let {
                ResultsOverlay(
                    results = it,
                    frameWidth = frameWidth,
                    frameHeight = frameHeight,
                    onClick = showDetailTajweed
                )
            }
        }
    }
}

@Composable
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
fun AudioPlayer(
    audioUrl: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current




}