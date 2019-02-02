package com.meetup.constraint.extension

import android.content.Context
import com.meetup.constraint.R

val Context.orientation: Int
    get() = resources.configuration.orientation

val Context.isTablet: Boolean
    get() = resources.getBoolean(R.bool.should_be_considered_tablet)