package unsiq.mtqmn23.jadda.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs

data class Salat(
    val time: String?,
    val name: String?
)

fun List<Salat>.getNearSalat(): Salat {
    val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

    return try {
        val nearestMealTime = minByOrNull { meal ->
            val currentTimeMillis = SimpleDateFormat("HH:mm", Locale.getDefault()).parse(currentTime)?.time
            val mealTimeMillis = SimpleDateFormat("HH:mm", Locale.getDefault()).parse(meal.time.toString())?.time
            abs((currentTimeMillis ?: 0) - (mealTimeMillis ?: 0))
        }
        Salat(nearestMealTime?.time, nearestMealTime?.name)
    } catch (e: Exception) {
        Salat("", "")
    }
}
