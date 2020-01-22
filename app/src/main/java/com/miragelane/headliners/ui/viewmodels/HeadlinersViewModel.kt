package com.miragelane.headliners.ui.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * The base view model that should be used for all ViewModels so that they manage disposables
 * for this view model.
 */
abstract class HeadlinersViewModel: ViewModel() {

    /**
     * Disposed when this ViewModel is cleared.
     */
    val lifecycleDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        this.lifecycleDisposable.dispose()
    }
}