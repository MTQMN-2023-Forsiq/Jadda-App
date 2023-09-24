package unsiq.mtqmn23.jadda.presentation.screen.salatpractice

import unsiq.mtqmn23.jadda.domain.model.salat.DataSalatItem

data class SalatPracticeUiState(
    val salatItems: List<DataSalatItem> = emptyList(),
    val currentPosePosition: Int = 0,
    val poseScore: Double = 0.0,
    val isExerciseDone: Boolean = false,
    val isTimerStarted: Boolean = false,
    val isExitDialogShow: Boolean = false,
    val timer: Int = -1,
    val exercisePoint: Int = 25,
    val isPerfectSoundActive: Boolean = false,
)
