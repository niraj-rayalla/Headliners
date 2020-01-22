package com.miragelane.headliners.ui.activities

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

/**
 * The base activity that handles lifecycle state for reactive extensions.
 */
abstract class HeadlinersActivity: AppCompatActivity() {

    //region State

    val lifecycleDisposable = CompositeDisposable()

    //endregion

    //region Overrides

    override fun onDestroy() {
        super.onDestroy()
        this.lifecycleDisposable.dispose()
    }

    //endregion

}