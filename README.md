# PersistentRecyclerView

仿京东首页，整体是个长列表(ParentRecyclerView)，内嵌子列表 - 商品feeds流(ChildRecyclerView)，且商品流可以左右滑动。

## 实现效果
点击可查看[截屏视频](http://sistone.top/capture/video.html?content=PersistentRecyclerView)：

<a href="http://sistone.top/capture/video.html?content=PersistentRecyclerView">
    <img src="capturedImage.jpg" width="460"/>
</a>

## 使用方法
1. 外部的长列表使用ParentRecyclerView；
2. 内嵌的子列表使用ChildRecyclerView；

Adapter及ViewHolder跟官方Recyclerview一样，ViewPager和ViewPager2可随意选用，均已内部兼容；

## 实现方案
通过**uiautomatorviewer**观察京东首页的View层级，会发现其长列表总体是个RecyclerView，设为ParentRecyclerView；而底部的商品feeds流是另一个Recyclerview，设为ChildRecyclerView。关键要解决这2个问题：

**问题一**：ParentRecyclerView触底时，Fling速率传递给ChildRecyclerView；<br/>
**问题二**：ChildRecyclerView触顶时，Fling速率传递给ParentRecyclerView；

这两个问题，都避不开一个问题，即：**如何获取当前RecyclerView的Fling速率？**

在阅读RecyclerView源码后，发现RecyclerView内部保存了一个mViewFlinger对象，而mViewFlinger内部持有OverScroller。于是，获取当前RecyclerView的Fling速率便迎刃而解: 

```kotlin
private val overScroller: OverScroller

init {
    // 1. mViewFlinger对象获取
    val viewFlingField = RecyclerView::class.java.getDeclaredField("mViewFlinger")
    viewFlingField.isAccessible = true
    var viewFlingObj = viewFlingField.get(this)

    // 2. overScroller对象获取
    val overScrollerFiled = viewFlingObj.javaClass.getDeclaredField("mOverScroller")
    overScrollerFiled.isAccessible = true
    overScroller = overScrollerFiled.get(viewFlingObj) as OverScroller
}

/**
 * 获取垂直方向的速率
 */
fun getVelocityY(): Int = (overScroller.currVelocity).toInt()

```

拿到当前RecyclerView的Fling速率之后，接下来就是将Fling速率传递给另一个RecyclerView了！这个比较简单，因为RecyclerView对外开放了fling()方法，可直接调用：
```kotlin
/**
 * Begin a standard fling with an initial velocity along each axis in pixels per second.
 * If the velocity given is below the system-defined minimum this method will return false
 * and no fling will occur.
 *
 * @param velocityX Initial horizontal velocity in pixels per second
 * @param velocityY Initial vertical velocity in pixels per second
 * @return true if the fling was started, false if the velocity was too low to fling or
 * LayoutManager does not support scrolling in the axis fling is issued.
 */
public boolean fling(int velocityX, int velocityY)
```

看起来好简单，就这么结束了？

当然不是！

**问题一**(Fling速率由Parent传递给Child)还要解决另一个问题：**ParentRecyclerView如何找到ViewPager中currentItem对应的ChildRecyclerView？**

我们都知道，ParentRecyclerView、ViewPager/ViewPager2、ChildRecyclerView三者的关系是1:1:N。ChildRecyclerView可以通过getParent()找到ParentRecyclerView，但是ParentRecyclerView如何找到ChildRecyclerView呢？现在摆在我们面前的是，Parent和Child之间至少还隔了一个ViewPager(或者ViewPager2)！如果布局再复杂一些，他们中间可能还隔着若干其它的ViewGroup！

这回涉及到另一个问题：**ViewPager/ViewPager2如何找到当前currentItem对应的子View？**

如果ViewPager/ViewPager2能够找到当前的子View，则ChildRecyclerView是不是可以将自己保存到这个子View里？



与此同时，我们还可以让ParentRecyclerView实现NestedScrollingParent3，借力安卓官方的思路，让ChildRecyclerView自动将fling传递给Parent。<br/><br/>
而至于第一个问题，Child寻找Parent容易，而Parent寻找Child却不太简单。所以ChildRecyclerView不仅要上报自己，还要上报ViewPager，以方便ParentRecyclerView找到自己！

当然细节代码较多，此处不再赘述，感兴趣的同学可自行Review代码即知。

## 另一种方案
对于长列表内嵌ViewPager以及ChildRecyclerView，官方控件中最接近这种效果的是CoordinatorLayout。所以，CoordinatorLayout改造之后，也能实现这样的效果，感兴趣的同学可去瞅瞅：[传送门](https://github.com/xmuSistone/PersistentCoordinatorLayout)

## Demo下载
[点击下载](https://github.com/xmuSistone/PersistentRecyclerView/blob/master/PersistentRecyclerView.apk?raw=true)
