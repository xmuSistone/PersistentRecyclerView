package com.stone.persistent.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.stone.persistent.recyclerview.R
import kotlinx.android.synthetic.main.item_menu.view.*

class MenuGridAdapter(context: Context, page: Int) : Adapter<MenuGridAdapter.MenuViewHolder>() {

    private var inflater: LayoutInflater = LayoutInflater.from(context)
    private lateinit var MENU_TITLES: Array<String>
    private lateinit var MENU_ICONS: Array<Int>

    init {
        buildData(page)
    }

    private fun buildData(page: Int) {
        if (page == 0) {
            MENU_TITLES = arrayOf(
                "京东超市",
                "数码电器",
                "京东服饰",
                "免费水果",
                "京东到家",
                "充值缴费",
                "9.9元拼",
                "领券",
                "赚钱",
                "PLUS会员"
            )

            MENU_ICONS = arrayOf(
                R.mipmap.menu_icon01,
                R.mipmap.menu_icon02,
                R.mipmap.menu_icon03,
                R.mipmap.menu_icon04,
                R.mipmap.menu_icon05,
                R.mipmap.menu_icon06,
                R.mipmap.menu_icon07,
                R.mipmap.menu_icon08,
                R.mipmap.menu_icon09,
                R.mipmap.menu_icon10
            )
        } else {
            MENU_TITLES = arrayOf(
                "京东国际",
                "京东拍卖",
                "唯品会",
                "沃尔玛",
                "京东旅行",
                "看病购药",
                "拍拍二手",
                "分享赚钱",
                "京东生鲜",
                "更多频道"
            )

            MENU_ICONS = arrayOf(
                R.mipmap.menu_icon11,
                R.mipmap.menu_icon12,
                R.mipmap.menu_icon13,
                R.mipmap.menu_icon14,
                R.mipmap.menu_icon15,
                R.mipmap.menu_icon16,
                R.mipmap.menu_icon17,
                R.mipmap.menu_icon18,
                R.mipmap.menu_icon19,
                R.mipmap.menu_icon20
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val itemView = inflater.inflate(R.layout.item_menu, parent, false)
        return MenuViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.itemView.item_menu_icon.setImageResource(MENU_ICONS[position])
        holder.itemView.item_menu_title.setText(MENU_TITLES[position])
    }

    override fun getItemCount(): Int {
        return MENU_TITLES.size
    }

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}