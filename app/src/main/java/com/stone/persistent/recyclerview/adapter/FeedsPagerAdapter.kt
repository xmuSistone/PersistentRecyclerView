package com.stone.persistent.recyclerview.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.stone.persistent.recyclerview.fragment.FeedsFragment

class FeedsPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragmentList = ArrayList<FeedsFragment>()

    init {
        fragmentList.add(FeedsFragment())
        fragmentList.add(FeedsFragment())
        fragmentList.add(FeedsFragment())
        fragmentList.add(FeedsFragment())
        fragmentList.add(FeedsFragment())
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }
}