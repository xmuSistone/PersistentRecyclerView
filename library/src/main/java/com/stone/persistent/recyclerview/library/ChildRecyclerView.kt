package com.stone.persistent.recyclerview.library

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * 内层的RecyclerView
 */
class ChildRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseRecyclerView(context, attrs, defStyleAttr) {

    private var parentRecyclerView: ParentRecyclerView? = null

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)

        // 是否已经停止scrolling
        if (state == SCROLL_STATE_IDLE) {
            // 这里是考虑到当整个childRecyclerView被detach之后，及时上报parentRecyclerView
            val velocityY = getVelocityY()
            if (velocityY < 0 && getListScrollY() == 0) {
                parentRecyclerView?.fling(0, velocityY)
            }
        }
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        if (e!!.action == MotionEvent.ACTION_DOWN) {
            this.stopFling()
        }
        return super.onInterceptTouchEvent(e)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        findParentRecyclerView()
    }

    private fun findParentRecyclerView() {
        var parentView = this.parent as View
        while (parentView != null) {
            if (parentView is ParentRecyclerView) {
                this.parentRecyclerView = parentView
                return
            }
            parentView = parentView.parent as View
        }
    }
}