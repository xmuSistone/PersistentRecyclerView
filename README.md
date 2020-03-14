# PersistentRecyclerView

仿京东首页，整体是个长列表(RecyclerView)，底部的商品列表部分可以左右滑动。

### 实现效果
此处放图

### 使用姿势
Adapter及ViewHolder跟官方Recyclerview一样，只是需要注意这2点：
1. 外部的长列表使用ParentRecyclerView；
2. 内嵌的子列表使用ChildRecyclerView；

仅此两点，别无其他，ViewPager和ViewPager2均已内部兼容，可随意选用；

### 实现方案
通过<b>uiautomatorviewer</b>观察京东首页的View层级，会发现其长列表总体是个RecyclerView，设为ParentRecyclerView；而底部的商品部分是另一个Recyclerview，设为ChildRecyclerView。

### 另一种方案
对于长列表内嵌ViewPager以及ChildRecyclerView，官方控件中最接近这种效果的是CoordinatorLayout。所以，CoordinatorLayout改造之后，也能实现这样的效果，感兴趣的同学可去瞅瞅：[传送门](https://github.com/xmuSistone/PersistentCoordinatorLayout)

### Demo下载
[点击下载](app-release.apk)
