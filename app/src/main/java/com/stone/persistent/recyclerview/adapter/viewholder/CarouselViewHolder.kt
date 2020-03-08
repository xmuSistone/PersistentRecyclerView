package com.stone.persistent.recyclerview.adapter.viewholder

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.stone.persistent.recyclerview.adapter.CarouselAdapter
import com.stone.persistent.recyclerview.widget.CarouselConstraintLayout
import kotlinx.android.synthetic.main.item_main_carousel.view.*

/**
 * 轮播图
 */
class CarouselViewHolder(itemView: View, activity: AppCompatActivity) :
    RecyclerView.ViewHolder(itemView) {

    init {
        // 1. 设置ViewPager的Adapter
        val carouselViewPager = itemView.home_carousel_viewpager2
        carouselViewPager.adapter = CarouselAdapter(activity)
        carouselViewPager.currentItem = 2000

        // 2. 指示器 & 轮播
        itemView.home_carousel_indicator.setViewPager2(carouselViewPager, 5)
        val carouselConstraintLayout = itemView as CarouselConstraintLayout
        carouselConstraintLayout.startCarousel(carouselViewPager)
    }
}