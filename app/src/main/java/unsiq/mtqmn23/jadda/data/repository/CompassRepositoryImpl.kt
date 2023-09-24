package unsiq.mtqmn23.jadda.data.repository

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import unsiq.mtqmn23.jadda.domain.model.compass.LocationStatus
import unsiq.mtqmn23.jadda.domain.repository.CompassRepository
import unsiq.mtqmn23.jadda.util.hasPermissions
import unsiq.mtqmn23.jadda.util.toRotationInDegrees
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.PI

@Singleton
class CompassRepositoryImpl @Inject constructor(
    private val sensorManager: SensorManager,
    @ApplicationContext private val context: Context,
) : CompassRepository {

    private val rotation = MutableStateFlow(0)
    private val accelerometerReading = FloatArray(3)
    private val magnetometerReading = FloatArray(3)

    private val rotationMatrix = FloatArray(9)
    private val orientationAngles = FloatArray(3)

    override fun startObservingRotation() {
        sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD).also { magneticField ->
            sensorManager.registerListener(
                sensorEventListener,
                magneticField,
                SensorManager.SENSOR_DELAY_NORMAL,
            )
        }

        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER).also { accelerometer ->
            sensorManager.registerListener(
                sensorEventListener,
                accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL,
            )
        }
    }

    override fun stopObservingRotation() {
        sensorManager.unregisterListener(sensorEventListener)
    }

    override fun getRotation() = rotation.asStateFlow()

    private val sensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            when (event?.sensor?.type) {
                Sensor.TYPE_MAGNETIC_FIELD -> {
                    System.arraycopy(
                        event.values,
                        0,
                        magnetometerReading,
                        0,
                        magnetometerReading.size
                    )
                    updateOrientationAngles()
                }
                Sensor.TYPE_ACCELEROMETER -> {
                    System.arraycopy(
                        event.values,
                        0,
                        accelerometerReading,
                        0,
                        accelerometerReading.size
                    )
                    updateOrientationAngles()
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            Timber.d("onAccuracyChanged: $sensor, $accuracy")
        }
    }

    private fun updateOrientationAngles() {
        SensorManager.getRotationMatrix(
            rotationMatrix,
            null,
            accelerometerReading,
            magnetometerReading
        )

        SensorManager.getOrientation(rotationMatrix, orientationAngles)

        rotation.value = (orientationAngles[0] * 180 / PI).toRotationInDegrees()
    }

    private val locationResult = MutableStateFlow<LocationStatus>(LocationStatus.Loading)

    private val locationRequest = LocationRequest.Builder(5000)
        .setMinUpdateIntervalMillis(1000)
        .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
        .build()

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            result.lastLocation?.let {
                val geocoder = Geocoder(context, Locale.getDefault())
                try {
                    val address = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                    locationResult.value = LocationStatus.Success(it, address?.get(0)?.locality ?: "-")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun startLocationUpdates() {
        if (context.hasPermissions(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)) {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null
            )
        } else {
            Timber.tag("Start-Location-Update").d("No Permission")
        }
    }

    override fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun getUserLocation() = locationResult

    override fun getDestinationLocation(): Flow<Location> = flow {
        try {
            val destination = Location("").apply {
                latitude = 21.422537173114037
                longitude = 39.82617222053955
            }
            emit(destination)
        } catch (e: Exception) {
            Timber.tag("Error").d("getDestinationLocation: ${e.message}")
            e.printStackTrace()
        }
    }
}