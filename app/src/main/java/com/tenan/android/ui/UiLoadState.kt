package com.tenan.android.ui

sealed class UiLoadState<T> {
    class Loading<T> : UiLoadState<T>()
    class Empty<T> : UiLoadState<T>()
    class Failed<T> : UiLoadState<T>()
    data class Available<T>(val data: T) : UiLoadState<T>()
}