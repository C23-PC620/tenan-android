package com.tenan.android.ui.feature.tourism

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tenan.android.data.Resource
import com.tenan.android.data.repository.TourismRepository
import com.tenan.android.entity.Tourism
import com.tenan.android.ui.UiLoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailTourismViewModel @Inject constructor(
    private val tourismRepository: TourismRepository
) : ViewModel() {

    private val _tourismUiState = MutableLiveData<UiLoadState<Tourism>>()
    val tourismUiState: LiveData<UiLoadState<Tourism>> get() = _tourismUiState

    fun getTourismById(tourismId: Int) {
        viewModelScope.launch {
            tourismRepository.getTourismById(tourismId).let { resource ->
                _tourismUiState.postValue(when (resource) {
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