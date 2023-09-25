package unsiq.mtqmn23.jadda.presentation.screen.compass

import unsiq.mtqmn23.jadda.domain.model.compass.DestinationStatus

data class CompassState(
    val destinationStatus: DestinationStatus? = null,
    val rotation: Int = 0,
    val addressCity: String = "-",
)