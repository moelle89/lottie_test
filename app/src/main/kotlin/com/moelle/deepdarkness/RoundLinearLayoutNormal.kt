package com.moelle.deepdarkness

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout

import com.moelle.deepdarkness.util.ViewUtils

/**
 * Created by ArmanSo on 4/16/17.
 */

class RoundLinearLayoutNormal @JvmOverloads constructor(
        context: Context? = null,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        initBackground()
    }

    private fun initBackground() {
        background = ViewUtils.generateBackgroundWithShadow(this, R.color.background,
                R.dimen.radius_corner, R.color.shadowColor, R.dimen.layoutElevation, Gravity.TOP)
    }
}
