package com.meetup.constraint.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.meetup.constraint.R

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
    }

    fun onMeetupDemoClick(view: View) {
        val intent = when (view.id) {
            R.id.three_button_layout_button -> Intent(this, ButtonTransitionActivity::class.java)
            R.id.constraint_set_layout_button -> Intent(this, ConstraintSetActivity::class.java)
            R.id.constraint_states_layout_button -> Intent(this, ConstraintStatesActivity::class.java)
            R.id.motion_layout_button -> Intent(this, MotionLayoutActivity::class.java)
            R.id.frameset_button -> Intent(this, KeyFrameSetActivity::class.java)
            R.id.relative_layout_button -> Intent(this, RelativeLayoutActivity::class.java)
            else -> Intent(this, ButtonTransitionActivity::class.java)
        }

        startActivity(intent)
    }
}
