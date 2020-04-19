package com.stone.persistent.recyclerview.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.stone.persistent.recyclerview.fragment.MenuGridFragment

class MenuPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        val menuGridFragment = MenuGridFragment()
        menuGridFragment.page = position
        return menuGridFragment
    }

    override fun getItemCount(): Int = 2
}