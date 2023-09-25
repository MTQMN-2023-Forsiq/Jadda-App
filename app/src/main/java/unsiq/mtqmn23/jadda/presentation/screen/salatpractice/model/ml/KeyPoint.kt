package unsiq.mtqmn23.jadda.presentation.screen.salatpractice.model.ml

import android.graphics.PointF

data class KeyPoint(val bodyPart: BodyPart, var coordinate: PointF, val score: Float)
