package com.stone.persistent.recyclerview.adapter.viewholder

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.stone.persistent.recyclerview.adapter.FeedsPagerAdapter
import kotlinx.android.synthetic.main.item_main_feeds.view.*

/**
 * 菜单ViewPager.ViewHolder
 */
class FeedsViewHolder(itemView: View, activity: AppCompatActivity) :
    RecyclerView.ViewHolder(itemView) {

    init {
        val feedsViewPager = itemView.main_feeds_view_pager
        feedsViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val feedsPagerAdapter = FeedsPagerAdapter(activity)
        feedsViewPager.adapter = feedsPagerAdapter
    }
}