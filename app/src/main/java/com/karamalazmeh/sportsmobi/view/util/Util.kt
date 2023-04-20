package com.karamalazmeh.sportsmobi.view.util

import android.graphics.Color
import androidx.core.graphics.ColorUtils

fun darkenColor(color: Int, factor: Float): Int {
    val a = Color.alpha(color)
    val r = Color.red(color)
    val g = Color.green(color)
    val b = Color.blue(color)
    return ColorUtils.blendARGB(color, Color.rgb((r * factor).toInt(), (g * factor).toInt(), (b * factor).toInt()), factor)
}