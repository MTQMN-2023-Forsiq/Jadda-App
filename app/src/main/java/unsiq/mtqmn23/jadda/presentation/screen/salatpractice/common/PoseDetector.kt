package unsiq.mtqmn23.jadda.presentation.screen.salatpractice.common

import android.graphics.Bitmap
import unsiq.mtqmn23.jadda.presentation.screen.salatpractice.model.ml.Person

interface PoseDetector : AutoCloseable {
    fun estimateSinglePose(bitmap: Bitmap): Person
    fun lastInferenceTimeNanos(): Long
}
