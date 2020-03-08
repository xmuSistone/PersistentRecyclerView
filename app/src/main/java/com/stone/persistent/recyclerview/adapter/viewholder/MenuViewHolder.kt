package com.stone.persistent.recyclerview.adapter.viewholder

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.stone.persistent.recyclerview.adapter.MenuPagerAdapter
import kotlinx.android.synthetic.main.item_main_menu.view.*

/**
 * 菜单ViewPager.ViewHolder
 */
class MenuViewHolder(itemView: View, activity: AppCompatActivity) :
    RecyclerView.ViewHolder(itemView) {

    init {
        itemView.home_menu_viewpager2.offscreenPageLimit = 2
        itemView.home_menu_viewpager2.adapter = MenuPagerAdapter(activity)
        itemView.home_menu_indicator.setViewPager2(itemView.home_menu_viewpager2, 2)
    }
}