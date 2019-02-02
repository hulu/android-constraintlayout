package com.meetup.constraint.model

data class TVShowResultResult(val result: TvShow)
data class TvShow(val name: String, val description: String?, val detailedDescription: DetailedDescription?)
data class DetailedDescription(val articleBody:String)