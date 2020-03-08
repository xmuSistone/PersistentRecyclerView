package com.stone.persistent.recyclerview.adapter.viewholder

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.stone.persistent.recyclerview.adapter.CarouselAdapter
import com.stone.persistent.recyclerview.helper.CarouselHelper
import com.stone.persistent.recyclerview.widget.CarouselConstraintLayout
import kotlinx.android.synthetic.main.item_main_carousel.view.*

/**
 * 轮播图
 */
class CarouselViewHolder(itemView: View, activity: AppCompatActivity) :
    RecyclerView.ViewHolder(itemView) {

    init {
        // 1. 设置ViewPager的Adapter
        val home_carousel_viewpager2 = itemView.home_carousel_viewpager2
        home_carousel_viewpager2.adapter = CarouselAdapter(activity)
        home_carousel_viewpager2.currentItem = 2000

        // 2. 指示器 & 轮播
        itemView.home_carousel_indicator.setViewPager2(home_carousel_viewpager2, 5)
        val carouselHelper = CarouselHelper(home_carousel_viewpager2)
        carouselHelper.start()

        val carouselConstraintLayout = itemView as CarouselConstraintLayout
        carouselConstraintLayout.setCarouselHelper(carouselHelper)
    }
}