package com.meetup.constraint.activity

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.meetup.constraint.R
import com.meetup.constraint.R.layout
import com.meetup.constraint.adapter.TvGuideAdapter
import com.meetup.constraint.viewmodel.TvShowsGuideViewModel
import com.meetup.constraint.extension.isTablet
import com.meetup.constraint.extension.orientation
import kotlinx.android.synthetic.main.player_portrait.*

class ConstraintSetActivity : AppCompatActivity() {

    private val constraintSet = ConstraintSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.player_portrait)
        guide_view.layoutManager = LinearLayoutManager(this)
        val viewModel = ViewModelProviders.of(this).get(TvShowsGuideViewModel::class.java)
        viewModel.fetchTvShows(application).observe(this, Observer { observer ->
            observer?.let { guide_view.adapter = TvGuideAdapter(it, TvGuideAdapter.TvViewType.CONSTRAINT) }
        })
        initConfig()
    }

    private fun initConfig() {
        //if needed
        if (this.orientation == Configuration.ORIENTATION_LANDSCAPE && this.isTablet) {
            applyTabletLandscapeConstraints()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (!this.isTablet) return

        setConstraints(newConfig)

    }

    private fun setConstraints(newConfig: Configuration) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            applyTabletLandscapeConstraints()
        } else {
            applyTabletPortraitConstraints()
        }
    }

    private fun applyTabletLandscapeConstraints() {
        constraintSet.clone(this, R.layout.player_tablet_landscape)
        constraintSet.applyTo(container)
    }

    private fun applyTabletPortraitConstraints() {
        constraintSet.clone(this, R.layout.player_portrait)
        constraintSet.applyTo(container)
    }
}