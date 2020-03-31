package com.stone.persistent.recyclerview

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.stone.persistent.recyclerview.adapter.MainListAdapter
import com.stone.persistent.recyclerview.extensions.immerseStatusBar
import com.stone.persistent.recyclerview.helper.SyncScrollHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var listAdapter: MainListAdapter? = null
    private var uiHandler: Handler? = null

    companion object {
        private const val MSG_TYPE_REFRESH_FINISHED = 0
        private const val MSG_TYPE_TABS_LOADED = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. 沉浸式状态栏
        immerseStatusBar()

        // 2. 列表RecyclerView
        main_recycler_view.layoutManager = LinearLayoutManager(this)
        initAdapter()
        main_recycler_view.adapter = listAdapter

        // 3. 滑动时同步
        val syncScrollHelper = SyncScrollHelper(this)
        syncScrollHelper.initLayout()
        syncScrollHelper.syncRecyclerViewScroll(main_recycler_view)
        syncScrollHelper.syncRefreshPullDown(main_refresh_layout)

        // 4. 下拉刷新处理 及加载tabs
        uiHandler = object : Handler() {
            override fun handleMessage(msg: Message) {
                if (msg.what == MSG_TYPE_REFRESH_FINISHED) {
                    // 下拉刷新完成
                    main_refresh_layout.finishRefresh()
                } else if (msg.what == MSG_TYPE_TABS_LOADED) {
                    // Tabs加载完成
                    listAdapter?.onTabsLoaded()
                }
            }
        }
        main_refresh_layout.setOnRefreshListener {
            // 100ms后，刷新成功
            uiHandler?.sendEmptyMessageDelayed(MSG_TYPE_REFRESH_FINISHED, 500L)
        }
    }

    /**
     * 初始化Adapter
     */
    private fun initAdapter() {
        listAdapter = MainListAdapter(this)
        listAdapter!!.setActionListener(object : MainListAdapter.IActionListener {
            override fun onLoadingTabs() {
                // 1500ms后，加载tabs成功
                uiHandler?.sendEmptyMessageDelayed(MSG_TYPE_TABS_LOADED, 800L)
            }
        })
    }
}
