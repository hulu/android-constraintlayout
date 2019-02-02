package com.meetup.constraint.repository

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.meetup.constraint.model.TVShowResultResult
import io.reactivex.Single

const val TV_SHOWS_FILE_NAME = "tv_shows_list.json"

class ShowsRepository {
    companion object {
        fun fetchShows(context: Application): Single<List<TVShowResultResult>> {
            return Single.fromCallable {
                val jsonString = context.assets.open(TV_SHOWS_FILE_NAME).bufferedReader().use {
                    it.readText()
                }
                Gson().fromJson<List<TVShowResultResult>>(jsonString, object : TypeToken<Collection<TVShowResultResult>>() {}.type)
            }

        }
    }
}
