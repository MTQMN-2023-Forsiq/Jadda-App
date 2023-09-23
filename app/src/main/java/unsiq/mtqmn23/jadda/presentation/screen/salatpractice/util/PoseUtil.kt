package unsiq.mtqmn23.jadda.presentation.screen.salatpractice.util

import android.content.Context
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import unsiq.mtqmn23.jadda.presentation.screen.salatpractice.model.PersonBodyAngle
import java.io.File
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.math.pow
import kotlin.math.sqrt

@Suppress("BlockingMethodInNonBlockingContext")
suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine { continuation ->
    ProcessCameraProvider.getInstance(this).also { future ->
        future.addListener(
            {
                continuation.resume(future.get())
            },
            executor
        )
    }
}

val Context.executor: Executor
    get() = ContextCompat.getMainExecutor(this)

suspend fun ImageCapture.takePicture(executor: Executor): File {
    val photoFile = withContext(Dispatchers.IO) {
        kotlin.runCatching {
            File.createTempFile("image", "jpg")
        }.getOrElse { e ->
            Log.e("TakePicture", "Failed to create temporary file", e)
            File("/dev/null")
        }
    }

    return suspendCoroutine { continuation ->
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        takePicture(
            outputOptions, executor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    continuation.resume(photoFile)
                }

                override fun onError(e: ImageCaptureException) {
                    Log.e("TakePhoto", "Image capture failed", e)
                    continuation.resumeWithException(e)
                }
            }
        )
    }
}

fun calculateSimilarity(actual: PersonBodyAngle, expected: PersonBodyAngle): Double {
    val sumOfSquaredDifferences = (actual.rightElbow - expected.rightElbow).toDouble().pow(2) +
            (actual.rightHip - expected.rightHip).toDouble().pow(2) +
            (actual.rightShoulder - expected.rightShoulder).toDouble().pow(2) +
            (actual.rightKnee - expected.rightKnee).toDouble().pow(2) +
            (actual.leftElbow - expected.leftElbow).toDouble().pow(2) +
            (actual.leftShoulder - expected.leftShoulder).toDouble().pow(2) +
            (actual.leftHip - expected.leftHip).toDouble().pow(2) +
            (actual.leftKnee - expected.leftKnee).toDouble().pow(2)

    val euclideanDistance = sqrt(sumOfSquaredDifferences)
    val maxDistance = sqrt(180.0.pow(2) * 8)  // Maximum possible distance

    return 1.0 - (euclideanDistance / maxDistance)
}
