# PersistentRecyclerView
### 实现目标
仿京东首页，整体是个长列表，但是底部的商品列表部分，可以左右滑动。

### 实现效果
此处放图

### 使用方式

### 实现方案
通过<b>uiautomatorviewer</b>观察下View层级，会发现京东首页的长列表总体是个RecyclerView，而商品部分是<b>另一个</b>内嵌的RecyclerView，而商品列表能够左右滑动是因为被放在了ViewPager中。

### 另一种方案
对于长列表内嵌ViewPager以及ChildRecyclerView，安卓官方控件中最接近这种效果的是CoordinatorLayout。所以，我用CoordinatorLayout也实现了这样的效果，感兴趣的同学可以去瞅瞅：[传送门](https://github.com/xmuSistone/PersistentCoordinatorLayout)

### Demo下载
[点击下载](app-release.apk)
