package com.stone.persistent.recyclerview.widget

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.ImageView
import com.stone.persistent.recyclerview.utils.Utils

class ProgressImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    var drawable = CircularProgressDrawable(context)

    init {
        drawable.strokeWidth = Utils.dp2px(context, 2f)
        drawable.strokeCap = Paint.Cap.ROUND
        drawable.arrowEnabled = true
        drawable.setColorSchemeColors(Color.WHITE)
        setImageDrawable(drawable)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        drawable.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        drawable.stop()
    }
}