package com.meetup.constraint.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.meetup.constraint.R
import com.meetup.constraint.adapter.TvGuideAdapter
import com.meetup.constraint.viewmodel.TvShowsGuideViewModel
import kotlinx.android.synthetic.main.player_relative_layout.*

class RelativeLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player_relative_layout)
        guide_view.layoutManager = LinearLayoutManager(this)
        val viewModel = ViewModelProviders.of(this).get(TvShowsGuideViewModel::class.java)
        viewModel.fetchTvShows(application).observe(this, Observer { observer ->
            observer?.let { guide_view.adapter = TvGuideAdapter(it, TvGuideAdapter.TvViewType.RELATIVE) }

        })
    }
}