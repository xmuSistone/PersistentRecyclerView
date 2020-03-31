package com.stone.persistent.recyclerview.extensions

import android.content.Context

/**
 * dp转换成px
 */
fun Context.dp2px(dpValue: Float): Float {
    var scale = resources.displayMetrics.density;
    return dpValue * scale + 0.5f
}


