package com.stone.persistent.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.stone.persistent.recyclerview.R
import kotlinx.android.synthetic.main.item_carousel.view.*

class CarouselAdapter(context: Context) : Adapter<CarouselAdapter.CarouselViewHolder>() {

    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val itemView = inflater.inflate(R.layout.item_carousel, parent, false)
        return CarouselViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val imgRes = when {
            position % 5 == 0 -> R.mipmap.carousel_1
            position % 5 == 1 -> R.mipmap.carousel_2
            position % 5 == 2 -> R.mipmap.carousel_3
            position % 5 == 3 -> R.mipmap.carousel_4
            else -> R.mipmap.carousel_5
        }
        holder.itemView.carousel_img.setImageResource(imgRes)
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    inner class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}