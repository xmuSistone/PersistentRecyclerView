package com.stone.persistent.recyclerview.library

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.viewpager2.widget.ViewPager2

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
        var viewPager2: ViewPager2? = null
        var lastTraverseView: View = this

        var parentView = this.parent as View
        while (parentView != null) {
            val parentClassName = parentView::class.java.canonicalName
            if ("androidx.viewpager2.widget.ViewPager2.RecyclerViewImpl" == parentClassName) {
                // 此处将ChildRecyclerView保存到ViewPager2.currentItem的tag中
                if (lastTraverseView != this) {
                    lastTraverseView.setTag(R.id.tag_saved_child_recycler_view, this)
                }
            } else if (parentView is ViewPager2) {
                // 碰到了ViewPager2，需要上报给ParentRecyclerView
                viewPager2 = parentView
            } else if (parentView is ParentRecyclerView) {
                parentView.setInnerViewPager(viewPager2)
                parentView.setChildPagerContainer(lastTraverseView)
                this.parentRecyclerView = parentView
                return
            }

            lastTraverseView = parentView
            parentView = parentView.parent as View
        }
    }
}