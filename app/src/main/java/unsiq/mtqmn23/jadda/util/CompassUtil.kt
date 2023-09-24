package unsiq.mtqmn23.jadda.util

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun Context.hasPermissions(vararg permissions: String) = permissions.all { permission ->
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

fun Float.toRotationInDegrees(): Int = (this.toInt() + 360) % 360
fun Double.toRotationInDegrees(): Int = (this.toInt() + 360) % 360