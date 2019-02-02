package com.meetup.constraint.activity

import android.os.Bundle
import android.transition.TransitionManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.meetup.constraint.R
import kotlinx.android.synthetic.main.button_transition_manager.*

class ButtonTransitionActivity : AppCompatActivity() {

    enum class ThreeButtonState {
        ONE_BUTTON,
        TWO_BUTTONS,
        LABEL_BUTTON
    }

    private var constraintSet = ConstraintSet()
    private var state: ThreeButtonState = ThreeButtonState.LABEL_BUTTON

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.button_transition_manager)
        positive_button.setOnClickListener { transitionToNextState() }
        negative_button.setOnClickListener { transitionToPreviousState() }
        setButtonState(state)
    }

    private fun transitionToNextState() {
        when (state) {
            ThreeButtonState.ONE_BUTTON -> setButtonState(ThreeButtonState.TWO_BUTTONS)
            ThreeButtonState.TWO_BUTTONS -> setButtonState(ThreeButtonState.LABEL_BUTTON)
            ThreeButtonState.LABEL_BUTTON -> setButtonState(ThreeButtonState.ONE_BUTTON)
        }
    }

    private fun transitionToPreviousState() {
        when(state) {
            ThreeButtonState.ONE_BUTTON -> setButtonState(ThreeButtonState.LABEL_BUTTON)
            ThreeButtonState.TWO_BUTTONS -> setButtonState(ThreeButtonState.ONE_BUTTON)
            ThreeButtonState.LABEL_BUTTON -> setButtonState(ThreeButtonState.TWO_BUTTONS)
        }
    }

    private fun setButtonState(newState: ThreeButtonState) {
        constraintSet.clone(container)

        when (newState) {
            ThreeButtonState.ONE_BUTTON -> showOneButton()
            ThreeButtonState.TWO_BUTTONS -> showTwoButton()
            ThreeButtonState.LABEL_BUTTON -> showLabelButton()
        }

        TransitionManager.beginDelayedTransition(container)
        constraintSet.applyTo(container)
    }

    private fun showOneButton() {
        constraintSet.setVisibility(negative_button.id, ConstraintSet.GONE)
        constraintSet.setHorizontalWeight(label_background.id, 0f)
        state = ThreeButtonState.ONE_BUTTON
    }

    private fun showTwoButton() {
        constraintSet.setVisibility(negative_button.id, ConstraintSet.VISIBLE)
        constraintSet.setHorizontalWeight(label_background.id, 0f)
        state = ThreeButtonState.TWO_BUTTONS
    }

    private fun showLabelButton() {
        constraintSet.setVisibility(negative_button.id, ConstraintSet.GONE)
        constraintSet.setHorizontalWeight(label_background.id, 3f)
        state = ThreeButtonState.LABEL_BUTTON
    }
}
