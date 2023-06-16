package com.tenan.android.ui.feature.explore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tenan.android.data.Resource
import com.tenan.android.data.repository.TourismRepository
import com.tenan.android.entity.Tourism
import com.tenan.android.ui.UiLoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val tourismRepository: TourismRepository
) : ViewModel() {

    private val _recommendedTourismUiState = MutableLiveData<UiLoadState<List<Tourism>>>(UiLoadState.Loading())
    val recommendedTourismUiState: LiveData<UiLoadState<List<Tourism>>> get() = _recommendedTourismUiState

    init {
        getRecommendedTourism()
    }

    fun getRecommendedTourism() {
        Timber.i("getRecommended")
        viewModelScope.launch {
            tourismRepository.getRecommendedTourism("Jakarta").let { resource ->
                _recommendedTourismUiState.postValue(when (resource) {
                    is Resource.Success -> {
                        resource.data?.let {
                            UiLoadState.Available(resource.data)
                        } ?: UiLoadState.Empty()
                    }

                    is Resource.Failed -> {
                        UiLoadState.Failed()
                    }
                })
            }
        }
    }

}