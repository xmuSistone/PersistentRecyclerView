package com.stone.persistent.recyclerview.library

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.NestedScrollingParent3

/**
 * 外层的RecylerView
 */
class ParentRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseRecyclerView(context, attrs, defStyleAttr), NestedScrollingParent3 {

    private var viewPagerContainer: ChildViewPagerContainer? = null

    init {
        this.overScrollMode = View.OVER_SCROLL_NEVER
        this.descendantFocusability = ViewGroup.FOCUS_BLOCK_DESCENDANTS
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev!!.action == MotionEvent.ACTION_DOWN) {
            this.stopFling()

            val childRecyclerView = viewPagerContainer?.getCurrentChildRecyclerView()
            childRecyclerView?.stopFling()
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        return true
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        if (target != null && target is ChildRecyclerView) {
            // 下面这一坨代码的主要目的是计算consumeY
            var consumeY = dy
            if (viewPagerContainer!!.top > 0) {
                if (viewPagerContainer!!.top - dy < 0) {
                    consumeY = viewPagerContainer!!.top
                }
            } else if (viewPagerContainer!!.top == 0) {
                val childScrollY = target.getListScrollY()
                consumeY = if (-dy < childScrollY) {
                    0
                } else {
                    dy + childScrollY
                }
            }

            if (consumeY != 0) {
                consumed.set(1, consumeY)
                this.scrollBy(0, dy)
            }
        }
    }

    override fun onScrollStateChanged(state: Int) {
        if (state == SCROLL_STATE_IDLE) {
            val velocityY = getVelocityY()
            if (velocityY > 0) {
                val childRecyclerView = viewPagerContainer?.getCurrentChildRecyclerView()
                childRecyclerView?.fling(0, velocityY)
            } else if (velocityY < 0) {
                this.fling(0, velocityY)
            }
        }
    }

    fun setChildViewPagerContainer(viewPagerContainer: ChildViewPagerContainer) {
        this.viewPagerContainer = viewPagerContainer
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        // do nothing
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        // do nothing
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
        // do nothing
    }

    override fun onStopNestedScroll(target: View, type: Int) {
        // do nothing
    }
}