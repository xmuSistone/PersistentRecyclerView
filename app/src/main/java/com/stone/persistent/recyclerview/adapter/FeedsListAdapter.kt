package com.stone.persistent.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.stone.persistent.recyclerview.R
import com.stone.persistent.recyclerview.utils.NetManager
import kotlinx.android.synthetic.main.item_feeds_product.view.*

/**
 * 商品流列表Adapter
 */
class FeedsListAdapter(context: FragmentActivity) :
    RecyclerView.Adapter<FeedsListAdapter.GoodsViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    private val dataList = NetManager.getProductList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsViewHolder {
        val itemView = inflater.inflate(R.layout.item_feeds_product, parent, false)
        return GoodsViewHolder(itemView = itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: GoodsViewHolder, position: Int) {
        holder.bindPosition(position)
    }

    inner class GoodsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * 刷新View
         */
        fun bindPosition(position: Int) {
            val itemData = dataList.get(position % 6)
            itemView.goods_title_tv.text = itemData.goodName
            itemView.goods_imageview.setImageResource(itemData.imagRes)

            val imageLp = itemView.goods_imageview.layoutParams as ConstraintLayout.LayoutParams
            imageLp.dimensionRatio = itemData.dimensionRatio.toString()
            itemView.goods_imageview.layoutParams = imageLp
        }
    }
}