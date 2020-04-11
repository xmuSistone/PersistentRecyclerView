package com.stone.persistent.recyclerview

import android.os.Bundle
import android.os.Handler
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

        // 0. 沉浸式状态栏
        immerseStatusBar()

        // 1. 初始化handler
        initHandler()

        // 2. 列表RecyclerView
        initAdapter()
        main_recycler_view.layoutManager = LinearLayoutManager(this)
        main_recycler_view.adapter = listAdapter

        // 3. 滑动时同步
        val syncScrollHelper = SyncScrollHelper(this)
        syncScrollHelper.initLayout()
        syncScrollHelper.syncRecyclerViewScroll(main_recycler_view)
        syncScrollHelper.syncRefreshPullDown(main_refresh_layout)

        // 4. 下拉刷新处理
        main_refresh_layout.setOnRefreshListener {
            uiHandler?.sendEmptyMessageDelayed(MSG_TYPE_REFRESH_FINISHED, 500L)
        }
    }

    private fun initHandler() {
        uiHandler = Handler {
            when {
                it.what == MSG_TYPE_REFRESH_FINISHED -> main_refresh_layout.finishRefresh()
                it.what == MSG_TYPE_TABS_LOADED -> listAdapter?.onTabsLoaded()
            }
            false
        }
    }

    /**
     * 初始化Adapter
     */
    private fun initAdapter() {
        listAdapter = MainListAdapter(this)
        listAdapter!!.setLoadingTabsListener {
            // 800ms后，加载tabs成功
            uiHandler?.sendEmptyMessageDelayed(MSG_TYPE_TABS_LOADED, 800L)
        }
    }
}
