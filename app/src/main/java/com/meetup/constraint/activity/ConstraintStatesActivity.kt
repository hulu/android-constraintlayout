package com.meetup.constraint.activity

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

class ConstraintStatesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.player_portrait)

        container.loadLayoutDescription(R.xml.player_states)

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
            container.setState(R.id.tablet_landscape_minimized, 0, 0)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (!this.isTablet) return

        setConstraints(newConfig)
    }

    private fun setConstraints(newConfig: Configuration) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            container.setState(R.id.tablet_landscape_minimized, 0, 0)
        } else {
            container.setState(R.id.tablet_portrait_minimized, 0, 0)
        }
    }
}