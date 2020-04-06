# PersistentRecyclerView

仿京东首页，整体是个长列表(ParentRecyclerView)，内嵌子列表 - 商品feeds流(ChildRecyclerView)，且商品流可以左右滑动。

## 实现效果
点击可查看[截屏视频](http://sistone.top/capture/video.html?content=PersistentRecyclerView)：

<a href="http://sistone.top/capture/video.html?content=PersistentRecyclerView">
    <img src="https://stone225.oss-cn-hangzhou.aliyuncs.com/jingdong.jpg" width="460"/>
</a>

## 使用方法
1. 外部的长列表使用ParentRecyclerView；
2. 内嵌的子列表使用ChildRecyclerView；

Adapter及ViewHolder跟官方Recyclerview一样，ViewPager和ViewPager2可随意选用，均已内部兼容；

## 实现方案
通过<b>uiautomatorviewer</b>观察京东首页的View层级，会发现其长列表总体是个RecyclerView，设为ParentRecyclerView；而底部的商品feeds流是另一个Recyclerview，设为ChildRecyclerView。关键要解决这3个问题：

<b>问题一</b>：ParentRecyclerView触底时，获取商品流ViewPager中currentItem对应的ChildRecyclerView；<br/>
<b>问题二</b>：ParentRecyclerView触底时，fling速率传递给ChildRecyclerView；<br/>
<b>问题三</b>：ChildRecyclerView触顶时，fling速率传递给ParentRecyclerView；

关于后两个问题，都需要在滑动临界的衔接处获取RecyclerView的fling速率。在阅读RecyclerView的源码后，发现其内部的OverScroller保存了我们想要的一切，包括fling速率！<br/>
与此同时，我们还可以让ParentRecyclerView实现NestedScrollingParent3，借力安卓官方的思路，让ChildRecyclerView自动将fling传递给Parent。<br/>
而至于第一个问题，Child寻找Parent容易，而Parent寻找Child却不太简单。所以ChildRecyclerView不仅要上报自己，还要上报ViewPager，以方便ParentRecyclerView找到自己！

当然细节代码较多，此处不再赘述，感兴趣的同学可自行Review代码即知。

## 另一种方案
对于长列表内嵌ViewPager以及ChildRecyclerView，官方控件中最接近这种效果的是CoordinatorLayout。所以，CoordinatorLayout改造之后，也能实现这样的效果，感兴趣的同学可去瞅瞅：[传送门](https://github.com/xmuSistone/PersistentCoordinatorLayout)

## Demo下载
[点击下载](https://github.com/xmuSistone/PersistentRecyclerView/blob/master/PersistentRecyclerView.apk?raw=true)
