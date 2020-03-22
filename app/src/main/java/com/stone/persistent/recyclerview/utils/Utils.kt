package com.stone.persistent.recyclerview.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager

class Utils {

    companion object {

        /**
         * 沉浸式状态栏
         */
        fun immerseStatusBar(activity: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.window.statusBarColor = Color.TRANSPARENT
                activity.window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            } else {
                activity.window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                )
            }
        }

        /**
         * 获取状态栏高度
         */
        fun getStatusBarHeight(context: Context): Int {
            var statusBarHeight = 0
            try {
                var c = Class.forName("com.android.internal.R\$dimen")
                var obj = c!!.newInstance()
                var field = c.getField("status_bar_height")
                var x = Integer.parseInt(field!!.get(obj).toString())
                statusBarHeight = context.resources.getDimensionPixelSize(x)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return statusBarHeight
        }

        fun dp2px(context: Context, dpValue: Float): Float {
            var scale = context.resources.displayMetrics.density;
            return dpValue * scale + 0.5f
        }

        /**
         * 获取屏幕宽度
         */
        fun getScreenWidth(activity: Activity): Int {
            val dm = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(dm)
            return dm.widthPixels
        }
    }
}