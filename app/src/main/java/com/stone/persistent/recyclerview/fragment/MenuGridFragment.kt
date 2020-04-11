package com.stone.persistent.recyclerview.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.stone.persistent.recyclerview.R
import com.stone.persistent.recyclerview.adapter.MenuGridAdapter
import kotlinx.android.synthetic.main.fragment_menu_grid.*

class MenuGridFragment : Fragment(R.layout.fragment_menu_grid) {

    var page : Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menu_recyclerview.layoutManager = GridLayoutManager(activity, 5)
        menu_recyclerview.adapter = MenuGridAdapter(activity!!, page)
    }
}