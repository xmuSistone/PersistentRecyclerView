package com.stone.persistent.recyclerview.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.stone.persistent.recyclerview.fragment.MenuGridFragment

class MenuPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return MenuGridFragment(position)
    }

    override fun getItemCount(): Int {
        return 2
    }
}