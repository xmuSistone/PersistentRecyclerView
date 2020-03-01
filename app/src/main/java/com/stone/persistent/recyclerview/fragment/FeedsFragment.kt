package com.stone.persistent.recyclerview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.stone.persistent.recyclerview.R
import com.stone.persistent.recyclerview.adapter.FeedsListAdapter
import com.stone.persistent.recyclerview.library.ChildRecyclerView

class FeedsFragment : Fragment() {

    private var childRecyclerView: ChildRecyclerView? = null
    private var listAdapter: FeedsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_feeds_list, null)
        childRecyclerView = rootView as ChildRecyclerView

        listAdapter = FeedsListAdapter(activity!!)

        childRecyclerView!!.layoutManager = LinearLayoutManager(activity)
        childRecyclerView!!.adapter = listAdapter

        return rootView
    }

    fun getRecyclerView(): ChildRecyclerView? {
        return childRecyclerView
    }
}