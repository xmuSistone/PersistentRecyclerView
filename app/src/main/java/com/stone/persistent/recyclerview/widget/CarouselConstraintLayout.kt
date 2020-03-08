package com.stone.persistent.recyclerview.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout
import com.stone.persistent.recyclerview.helper.CarouselHelper

/**
 * ViewPager2是final，无法继承，需要在container中处理触摸事件
 */
class CarouselConstraintLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var carouselHelper: CarouselHelper? = null

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (ev!!.action == MotionEvent.ACTION_DOWN) {
            carouselHelper?.stop()
        } else if (ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_CANCEL) {
            carouselHelper?.start()
        }
        return super.onInterceptTouchEvent(ev)
    }

    fun setCarouselHelper(carouselHelper: CarouselHelper) {
        this.carouselHelper = carouselHelper
    }
}