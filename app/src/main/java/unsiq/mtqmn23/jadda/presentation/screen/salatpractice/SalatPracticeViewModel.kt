package unsiq.mtqmn23.jadda.presentation.screen.salatpractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import unsiq.mtqmn23.jadda.domain.repository.SalatRepository
import unsiq.mtqmn23.jadda.presentation.screen.salatpractice.model.PersonBodyAngle
import unsiq.mtqmn23.jadda.presentation.screen.salatpractice.util.calculateSimilarity
import unsiq.mtqmn23.jadda.util.toPersonBodyAngle
import javax.inject.Inject

@HiltViewModel
class SalatPracticeViewModel @Inject constructor(
    private val salatRepository: SalatRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(SalatPracticeUiState())
    val state = _state.asStateFlow()

    fun onEvent(event: SalatPracticeEvent) {
        when (event) {
            is SalatPracticeEvent.AddSalatItems -> {
                _state.update {
                    it.copy(
                        salatItems = event.salatItems
                    )
                }
            }
            is SalatPracticeEvent.OnPosePerfect -> {
                countDownTimer(
                    currentPosePosition = event.currentPosePosition
                ).start()
            }
            is SalatPracticeEvent.ResetScore -> {
                _state.update {
                    it.copy(
                        poseScore = 0.0
                    )
                }
            }
            is SalatPracticeEvent.SetScore -> {
                _state.update {
                    it.copy(
                        poseScore = event.actualAngle.getSimilarityScore(
                            it.salatItems[it.currentPosePosition].movementAngle.toPersonBodyAngle()
                        )
                    )
                }
            }
            is SalatPracticeEvent.OnShowHideExitDialog -> {
                _state.update {
                    it.copy(
                        isExitDialogShow = event.isExitDialogShow
                    )
                }
            }
        }
    }

    private fun PersonBodyAngle.getSimilarityScore(expectedScore: PersonBodyAngle?): Double {
        return if (expectedScore != null) {
            calculateSimilarity(this, expectedScore)
        } else 0.0
    }

    private fun countDownTimer(currentPosePosition: Int) = viewModelScope.launch {
        (8 downTo 0).forEach { timer ->
            _state.update {
                it.copy(
                    isTimerStarted = true,
                    timer = timer
                )
            }
            delay(1000)
        }
        _state.update {
            val posesSize = it.salatItems.size
            val isExerciseDone = currentPosePosition == posesSize - 1
            if (isExerciseDone) {
                _state.update {
                    it.copy(
                        isExerciseDone = true
                    )
                }
            }
            it.copy(
                currentPosePosition = if (currentPosePosition != posesSize - 1)
                    currentPosePosition + 1 else posesSize - 1,
                poseScore = 0.0,
                timer = -1,
                isTimerStarted = false
            )
        }
    }

//    private fun markActivityAsDone(burnedCalorie: Int) = viewModelScope.launch {
//        userUseCase.addUserPoints(25).collect { result ->
//            when (result) {
//                is Result.Loading -> {}
//                is Result.Success -> {
//                    _state.update {
//                        it.copy(
//                            isExerciseDone = true,
//                            exercisePoint = result.data.pointsAdded ?: 0
//                        )
//                    }
//                }
//
//                is Result.Error -> {
//                    _state.update {
//                        it.copy(
//                            isExerciseDone = true,
//                            exercisePoint = 0
//                        )
//                    }
//                }
//            }
//        }
//    }
}