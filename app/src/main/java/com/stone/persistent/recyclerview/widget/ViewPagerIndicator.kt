package com.stone.persistent.recyclerview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.stone.persistent.recyclerview.R
import com.stone.persistent.recyclerview.extensions.dp2px

/**
 * ViewPager2的指示器
 */
class ViewPagerIndicator @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var viewPager2: ViewPager2? = null
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var largerWidth = 10f
    private var smallerWidth = 10f
    private var rectHeight = 4f
    private var interval = 4f
    private var dotsCount = 0

    private var selectedColor: Int = Color.WHITE
    private var normalColor = Color.parseColor("#afdddddd")

    init {
        paint.style = Paint.Style.FILL
        largerWidth = context.dp2px(12f)
        smallerWidth = context.dp2px(7f)
        rectHeight = context.dp2px(2f)
        interval = context.dp2px(4f)

        val attr =
            context.getTheme().obtainStyledAttributes(attrs, R.styleable.indicator, defStyleAttr, 0)
        normalColor = attr.getColor(R.styleable.indicator_normalColor, normalColor)
        selectedColor = attr.getColor(R.styleable.indicator_selectedColor, selectedColor)
        attr.recycle()
    }

    fun setViewPager2(viewPager2: ViewPager2, realCount: Int) {
        this.dotsCount = realCount
        this.viewPager2 = viewPager2;
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                invalidate()
            }
        })
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (dotsCount > 1) {
            val width = (smallerWidth + interval) * (dotsCount - 1) + largerWidth
            val height = rectHeight + 1
            setMeasuredDimension(width.toInt(), height.toInt())
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val adapter = viewPager2?.adapter
        if (adapter !== null) {
            var startX = 0f
            for (index in 0..dotsCount) {
                val realIndex = viewPager2!!.currentItem % dotsCount
                var thisWidth = if (index == realIndex) {
                    paint.color = selectedColor
                    largerWidth
                } else {
                    paint.color = normalColor
                    smallerWidth
                }

                var fromX = startX
                var fromY = 0f
                var toX = startX + thisWidth
                var toY = rectHeight

                canvas.drawRoundRect(fromX, fromY, toX, toY, rectHeight / 2, rectHeight / 2, paint)
                startX = toX + interval
            }
        }
    }
}