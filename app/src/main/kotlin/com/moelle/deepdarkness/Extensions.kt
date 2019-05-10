package com.moelle.deepdarkness

import android.content.Context
import android.util.TypedValue

fun Context.resolveAttribute(attr: Int): Int {
    val typedValue = TypedValue()
    this.theme.resolveAttribute(attr, typedValue, true)
    return typedValue.data
}
