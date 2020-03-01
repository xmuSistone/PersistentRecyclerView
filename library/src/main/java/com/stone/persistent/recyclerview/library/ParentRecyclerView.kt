package com.stone.persistent.recyclerview.library

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.NestedScrollingParent3
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2

/**
 * 外层的RecylerView
 */
class ParentRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseRecyclerView(context, attrs, defStyleAttr), NestedScrollingParent3 {

    private var childPagerContainer: View? = null

    private var innerViewPager: ViewPager2? = null

    private var doNotInterceptTouchEvent: Boolean = false

    init {
        this.overScrollMode = View.OVER_SCROLL_NEVER
        this.descendantFocusability = ViewGroup.FOCUS_BLOCK_DESCENDANTS
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        if (e!!.action == MotionEvent.ACTION_DOWN) {
            // 1. 是否禁止拦截
            doNotInterceptTouchEvent =
                childPagerContainer != null && childPagerContainer!!.top < e!!.getY()

            // 2. 停止Fling
            this.stopFling()
            val childRecyclerView = findCurrentChildRecyclerView()
            childRecyclerView?.stopFling()
        }

        return if (doNotInterceptTouchEvent) {
            false
        } else {
            super.onInterceptTouchEvent(e)
        }
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        return true
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        if (target != null && target is ChildRecyclerView) {
            // 下面这一坨代码的主要目的是计算consumeY
            var consumeY = dy
            if (childPagerContainer!!.top > 0) {
                if (childPagerContainer!!.top - dy < 0) {
                    consumeY = childPagerContainer!!.top
                }
            } else if (childPagerContainer!!.top == 0) {
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
                val childRecyclerView = findCurrentChildRecyclerView()
                childRecyclerView?.fling(0, velocityY)
            } else if (velocityY < 0) {
                this.fling(0, velocityY)
            }
        }
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

    /**
     * 获取当前的ChildRecyclerView
     */
    private fun findCurrentChildRecyclerView(): ChildRecyclerView? {
        if (innerViewPager != null) {
            val layoutManagerFiled = ViewPager2::class.java.getDeclaredField("mLayoutManager")
            layoutManagerFiled.isAccessible = true
            val pagerLayoutManager = layoutManagerFiled.get(innerViewPager) as LinearLayoutManager
            var currentChild = pagerLayoutManager.findViewByPosition(innerViewPager!!.currentItem)

            if (currentChild is ChildRecyclerView) {
                return currentChild
            } else {
                val tagView = currentChild?.getTag(R.id.tag_saved_child_recycler_view)
                if (tagView is ChildRecyclerView) {
                    return tagView
                }
            }
        }
        return null
    }

    fun setInnerViewPager(viewPager2: ViewPager2?) {
        this.innerViewPager = viewPager2
    }

    fun setChildPagerContainer(childPagerContainer: View) {
        this.childPagerContainer = childPagerContainer
    }
}