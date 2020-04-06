package com.stone.persistent.recyclerview.helper

import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.viewpager2.widget.ViewPager2

class CarouselHelper(viewPager2: ViewPager2) : Handler(Looper.getMainLooper()) {

    private val viewPager2 = viewPager2

    companion object {
        private const val LOOP_INTERVAL = 3000L
    }

    override fun handleMessage(msg: Message?) {
        // 1. 切换到下一个item
        val nextItem = viewPager2.currentItem + 1
        viewPager2.currentItem = nextItem

        // 2. 开启下一次的轮播
        this.sendEmptyMessageDelayed(1, LOOP_INTERVAL)
    }

    fun start() {
        this.removeCallbacksAndMessages(null)
        this.sendEmptyMessageDelayed(1, LOOP_INTERVAL)
    }

    fun stop() {
        this.removeCallbacksAndMessages(null)
    }
}