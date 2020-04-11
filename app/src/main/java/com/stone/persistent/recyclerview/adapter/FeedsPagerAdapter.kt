package com.stone.persistent.recyclerview.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.stone.persistent.recyclerview.fragment.FeedsFragment

class FeedsPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return FeedsFragment()
    }

    override fun getItemCount(): Int {
        return 5
    }
}