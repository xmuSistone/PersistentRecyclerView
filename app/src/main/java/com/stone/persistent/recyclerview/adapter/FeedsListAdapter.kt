package com.stone.persistent.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.stone.persistent.recyclerview.R

/**
 * 商品流列表Adapter
 */
class FeedsListAdapter(context: FragmentActivity) :
    RecyclerView.Adapter<FeedsListAdapter.GoodsViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsViewHolder {
        val itemView = inflater.inflate(R.layout.item_feeds_product, parent, false)
        return GoodsViewHolder(itemView = itemView)
    }

    override fun getItemCount(): Int {
        return 100
    }

    override fun onBindViewHolder(holder: GoodsViewHolder, position: Int) {
    }

    inner class GoodsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}