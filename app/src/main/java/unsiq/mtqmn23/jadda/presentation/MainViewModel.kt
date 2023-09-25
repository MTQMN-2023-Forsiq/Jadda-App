package unsiq.mtqmn23.jadda.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import unsiq.mtqmn23.jadda.domain.model.salat.DataSalatItem
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _salatItems = MutableStateFlow<List<DataSalatItem>>(emptyList())
    val salatItems = _salatItems.asStateFlow()

    fun setData(items: List<DataSalatItem>) {
        _salatItems.value = items
    }
}