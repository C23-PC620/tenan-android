package com.tenan.android.ui.feature.search.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.tenan.android.data.repository.TourismRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val tourismRepository: TourismRepository
) : ViewModel() {

    fun getTourismByQuery(query: String) =
        tourismRepository
            .searchTourism(query = query)
            .cachedIn(viewModelScope)

}