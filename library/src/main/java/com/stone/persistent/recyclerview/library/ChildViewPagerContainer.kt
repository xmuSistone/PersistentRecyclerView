package com.stone.persistent.recyclerview.library

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * 这个是用来包装ViewPager的
 */
class ChildViewPagerContainer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var childProvider: IChildProvider? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        connectParentRecyclerView()
    }

    /**
     * 将自己上报给ParentRecyclerView
     */
    private fun connectParentRecyclerView() {
        var parentView = this.parent as View
        while (parentView != null) {
            if (parentView is ParentRecyclerView) {
                parentView.setChildViewPagerContainer(this)
                return
            }
            parentView = parentView.parent as View
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (ev!!.action == MotionEvent.ACTION_DOWN) {
            // 落子无悔，ParentRecyclerView禁止拦截Touch事件
            parent.requestDisallowInterceptTouchEvent(true)
        }
        return super.onInterceptTouchEvent(ev)
    }

    /**
     * 获取当前ChildRecyclerView
     */
    fun getCurrentChildRecyclerView(): ChildRecyclerView? {
        if (childProvider != null) {
            return childProvider!!.getCurrentChildRecyclerView()
        }
        return null
    }

    /**
     * 外围传入
     */
    fun setChildProvider(childProvider: IChildProvider) {
        this.childProvider = childProvider
    }

    /**
     * 要完成惯性传导，需要提供IChildProvider给ChildViewPagerContainer
     */
    interface IChildProvider {
        /**
         * 获取当前正在展示的ChildRecyclerView
         */
        fun getCurrentChildRecyclerView(): ChildRecyclerView?
    }
}