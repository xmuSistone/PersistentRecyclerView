package com.stone.persistent.recyclerview.utils

import com.stone.persistent.recyclerview.R
import com.stone.persistent.recyclerview.model.ProductItemModel

open class NetManager {

    companion object {

        /**
         * 获取商品列表
         */
        fun getProductList(): ArrayList<ProductItemModel> {
            val dataList = ArrayList<ProductItemModel>()
            for (i in 1..10) {
                dataList.add(
                    ProductItemModel(
                        "单筒手机拍照望远镜 高倍高清微光夜视成人非红外演唱会望眼镜 升级版 送手机夹 三脚架",
                        R.mipmap.goods1,
                        1.084f,
                        "112.00"
                    )
                )
                dataList.add(
                    ProductItemModel(
                        "男士短款钱包复古真皮零钱夹头层牛皮竖款皮包 黑色",
                        R.mipmap.goods2,
                        0.776946f,
                        "299.90"
                    )
                )
                dataList.add(
                    ProductItemModel(
                        "男士保暖内衣高领打底衫冬季加绒加厚紧身防寒上衣单件秋衣男内穿 6611加绒黑色 M",
                        R.mipmap.goods3,
                        0.776946f,
                        "65.50"
                    )
                )

                dataList.add(
                    ProductItemModel(
                        "红木把玩健身手球老人生日礼物送长辈祝寿老年人实用礼物品爸爸父亲生日礼物",
                        R.mipmap.goods4,
                        0.756f,
                        "88.00"
                    )
                )

                dataList.add(
                    ProductItemModel(
                        "恒源祥羊毛衫女中长款2018新款秋冬韩版半高领打底毛衣长袖针织衫",
                        R.mipmap.goods5,
                        0.5784f,
                        "99.00"
                    )
                )

                dataList.add(
                    ProductItemModel(
                        "百圣牛PASNEW新款运动手表 多功能防水表夜光秒表倒计时游泳表学生大数字电子表",
                        R.mipmap.goods6,
                        0.776946f,
                        "75.90"
                    )
                )
            }
            return dataList
        }
    }

}