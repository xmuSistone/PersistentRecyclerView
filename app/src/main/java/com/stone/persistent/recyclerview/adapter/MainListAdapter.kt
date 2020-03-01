package com.stone.persistent.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.stone.persistent.recyclerview.R
import kotlinx.android.synthetic.main.item_main_feeds.view.*

class MainListAdapter(context: AppCompatActivity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val context: AppCompatActivity = context

    private val inflater = LayoutInflater.from(context)

    companion object {
        private const val VIEW_TYPE_BANNER = 0
        private const val VIEW_TYPE_MENU = 1
        private const val VIEW_TYPE_FEEDS = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_BANNER
            1 -> VIEW_TYPE_MENU
            else -> VIEW_TYPE_FEEDS
        }
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_BANNER -> {
                val itemView = inflater.inflate(R.layout.item_main_banner, parent, false)
                EmptyViewHolder(itemView)
            }

            VIEW_TYPE_MENU -> {
                val itemView = inflater.inflate(R.layout.item_main_menu, parent, false)
                EmptyViewHolder(itemView)
            }

            else -> {
                val itemView = inflater.inflate(R.layout.item_main_feeds, parent, false)
                FeedsViewHolder(itemView)
            }
        }
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // do nothing at the moment
    }

    inner class FeedsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            val feedsViewPager = itemView.main_feeds_view_pager
            feedsViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            val feedsPagerAdapter = FeedsPagerAdapter(context)
            feedsViewPager.adapter = feedsPagerAdapter
        }
    }

    inner class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}