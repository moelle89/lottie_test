package com.moelle.deepdarkness

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout

import com.moelle.deepdarkness.util.ViewUtils

/**
 * Created by ArmanSo on 4/16/17.
 */

class RoundLinerLayoutNormal : LinearLayout {
    constructor(context: Context) : super(context) {
        initBackground()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initBackground()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initBackground()
    }

    private fun initBackground() {
        // TODO add whatever attribute you want to use or just remove the comment
        background = ViewUtils.generateBackgroundWithShadow(this, context.resolveAttribute(R.attr.background),
                R.dimen.radius_corner, R.color.shadowColor, R.dimen.layoutElevation, Gravity.CENTER)
    }
}
