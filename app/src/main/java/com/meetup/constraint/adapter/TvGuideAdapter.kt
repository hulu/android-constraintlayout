package com.meetup.constraint.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.meetup.constraint.R
import com.meetup.constraint.model.TVShowResultResult
import com.meetup.constraint.model.TvShow

class TvGuideAdapter(private val tvShows: List<TVShowResultResult>, private val viewType: TvViewType) : RecyclerView.Adapter<TvShowViewHolder>() {
    enum class TvViewType(val type: Int) {
        CONSTRAINT(0),
        RELATIVE(1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = when(viewType) {
            TvViewType.CONSTRAINT.type -> LayoutInflater.from(parent.context).inflate(R.layout.tv_show_guide_item_constraint_layout, parent, false)
            TvViewType.RELATIVE.type -> LayoutInflater.from(parent.context).inflate(R.layout.tv_show_guide_item_relative_layout, parent, false)
            else -> LayoutInflater.from(parent.context).inflate(R.layout.tv_show_guide_item_constraint_layout, parent, false)
        }
        return TvShowViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(tvShows[position].result)
    }

    override fun getItemViewType(position: Int): Int {
        return viewType.type
    }
}

class TvShowViewHolder(private val holderView: View) : RecyclerView.ViewHolder(holderView) {
    fun bind(tvShow: TvShow) {
        holderView.findViewById<TextView>(R.id.tv_show_item_name).text = tvShow.name
        holderView.findViewById<TextView>(R.id.tv_show_item_description).text = tvShow.detailedDescription?.articleBody?:tvShow.description
    }
}