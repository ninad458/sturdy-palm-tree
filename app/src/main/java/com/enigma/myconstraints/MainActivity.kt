package com.enigma.myconstraints

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.TransitionManager

class MainActivity : AppCompatActivity() {

    private val diamondRadius by lazy { RADIUS.toPx.toInt() }

    private val fab by lazy { findViewById<View>(R.id.fab) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val first = findViewById<View>(R.id.first)
        val second = findViewById<View>(R.id.second)
        val third = findViewById<View>(R.id.third)
        val forth = findViewById<View>(R.id.forth)
        val diamondViews = listOf(first, second, third, forth)
        fab.tag = true
        fab.setOnClickListener { view ->
            if (view.tag !is Boolean) return@setOnClickListener
            if (diamondViews.any { it.layoutParams !is ConstraintLayout.LayoutParams })
                return@setOnClickListener
            val isOpen = view.tag as Boolean
            val radius = if (isOpen) 0 else diamondRadius
            TransitionManager.beginDelayedTransition(findViewById(R.id.root))
            for (v in diamondViews) {
                val layoutParams = v.layoutParams as ConstraintLayout.LayoutParams
                layoutParams.circleRadius = radius
                v.layoutParams = layoutParams
            }
            view.tag = !isOpen
        }
    }

    companion object {
        private const val RADIUS = 90
    }
}