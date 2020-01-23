package com.miragelane.headliners.ui.activities

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.miragelane.headliners.R
import com.miragelane.headliners.api.HeadlinersApi
import com.miragelane.headliners.extensions.disposedBy
import com.miragelane.headliners.ui.viewmodels.HeadlinesActivityViewModel

import kotlinx.android.synthetic.main.activity_headlines.*

/**
 * Shows all headlines. Each headline will be clickable and it opens the headline in the browser.
 */
class HeadlinesActivity : HeadlinersActivity() {

    //region State

    private lateinit var viewModel: HeadlinesActivityViewModel

    //endregion

    //region Setup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_headlines)

        // Create the view model
        this.viewModel = ViewModelProviders.of(this).get(HeadlinesActivityViewModel::class.java)

        // Now bind to that view model
        this.bindToViewModel()
    }

    private fun bindToViewModel() {
    }

    //endregion
}
