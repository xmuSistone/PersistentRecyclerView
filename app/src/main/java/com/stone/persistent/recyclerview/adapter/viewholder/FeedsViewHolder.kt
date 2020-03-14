package com.stone.persistent.recyclerview.adapter.viewholder

import android.graphics.Color
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.stone.persistent.recyclerview.adapter.FeedsPagerAdapter
import kotlinx.android.synthetic.main.item_main_feeds.view.*

/**
 * 菜单ViewPager.ViewHolder
 */
class FeedsViewHolder(itemView: View, activity: AppCompatActivity) :
    RecyclerView.ViewHolder(itemView) {

    private val tabList = ArrayList<TextView>()
    private val feedsViewPager = itemView.main_feeds_view_pager

    companion object {
        private val COLOR_TAB_NORMAL by lazy { Color.parseColor("#333333") }
        private val COLOR_TAB_SELECTED by lazy { Color.parseColor("#ff0000") }
    }

    init {
        feedsViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        feedsViewPager.adapter = FeedsPagerAdapter(activity)

        bindTabs()
    }

    private fun bindTabs() {
        val tabClickListener = View.OnClickListener {
            val index = tabList.indexOf(it)
            feedsViewPager.currentItem = index
        }

        tabList.add(itemView.feeds_tab1)
        tabList.add(itemView.feeds_tab2)
        tabList.add(itemView.feeds_tab3)
        tabList.add(itemView.feeds_tab4)
        tabList.add(itemView.feeds_tab5)
        for (itemTab in tabList) {
            itemTab.setOnClickListener(tabClickListener)
        }

        feedsViewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                onTabChanged(position)
            }
        })
        onTabChanged(0)
    }

    private fun onTabChanged(position: Int) {
        val num = tabList.size
        for (i in 0 until num) {
            val itemTab = tabList[i]
            if (i == position) {
                itemTab.setTextColor(COLOR_TAB_SELECTED)
                itemTab.paint.isFakeBoldText = true
                itemTab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
            } else {
                itemTab.setTextColor(COLOR_TAB_NORMAL)
                itemTab.paint.isFakeBoldText = false
                itemTab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
            }
        }
    }
}