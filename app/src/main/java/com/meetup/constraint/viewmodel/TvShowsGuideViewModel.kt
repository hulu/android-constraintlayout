package com.meetup.constraint.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.meetup.constraint.model.TVShowResultResult
import com.meetup.constraint.repository.ShowsRepository
import io.reactivex.schedulers.Schedulers

class TvShowsGuideViewModel: ViewModel() {
    private lateinit var tvShows: LiveData<List<TVShowResultResult>>

    fun fetchTvShows(application: Application): LiveData<List<TVShowResultResult>> {
        if (!::tvShows.isInitialized) {
            tvShows = LiveDataReactiveStreams.fromPublisher(ShowsRepository.fetchShows(application)
                    .subscribeOn(Schedulers.io()).toFlowable())
        }
        return tvShows
    }
}