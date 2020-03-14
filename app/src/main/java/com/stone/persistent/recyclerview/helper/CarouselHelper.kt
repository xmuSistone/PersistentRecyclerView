package com.stone.persistent.recyclerview.helper

import android.os.Handler
import android.os.Looper
import androidx.viewpager2.widget.ViewPager2

class CarouselHelper(viewPager2: ViewPager2) {

    private var carouselThread: Thread? = null
    private var handler: Handler = Handler(Looper.getMainLooper()) {
        viewPager2.currentItem = viewPager2.currentItem + 1
        true
    }

    init {
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
            }

        })

        viewPager2.setOnDragListener { _, _ -> true }
    }

    fun start() {
        carouselThread?.interrupt()

        carouselThread = Thread {
            try {
                while (true) {
                    Thread.sleep(3000)
                    handler.sendEmptyMessage(1)
                }
            } catch (e: Throwable) {

            }
        }
        carouselThread!!.start()
    }

    fun stop() {
        carouselThread?.interrupt()
        carouselThread = null
    }
}