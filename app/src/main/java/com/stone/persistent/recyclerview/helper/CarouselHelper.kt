package com.stone.persistent.recyclerview.helper

import android.os.Handler
import android.os.Looper
import android.view.DragEvent
import android.view.View
import androidx.viewpager2.widget.ViewPager2

class CarouselHelper(viewPager2: ViewPager2) {

    private var carouselThread: Thread? = null
    private var handler: Handler

    init {
        handler = Handler(Looper.getMainLooper()) {
            viewPager2.currentItem = viewPager2.currentItem + 1
            true;
        }

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
            }

        })

        viewPager2.setOnDragListener(object: View.OnDragListener {
            override fun onDrag(v: View?, event: DragEvent?): Boolean {
                return true
            }
        })
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
    }
}