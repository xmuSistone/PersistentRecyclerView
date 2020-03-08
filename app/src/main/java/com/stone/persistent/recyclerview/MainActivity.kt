package com.stone.persistent.recyclerview

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.stone.persistent.recyclerview.adapter.MainListAdapter
import com.stone.persistent.recyclerview.helper.SyncScrollHelper
import com.stone.persistent.recyclerview.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. 沉浸式状态栏
        Utils.immerseStatusBar(this)

        // 2. 列表RecyclerView
        main_recycler_view.layoutManager = LinearLayoutManager(this)
        main_recycler_view.adapter = MainListAdapter(this)

        // 3. 滑动时同步
        val syncScrollHelper = SyncScrollHelper(this)
        syncScrollHelper.initLayout()
        syncScrollHelper.syncRecyclerViewScroll(main_recycler_view)
        syncScrollHelper.syncRefreshPullDown(main_refresh_layout)

        // 4. 下拉刷新处理
        handler = object : Handler() {
            override fun handleMessage(msg: Message?) {
                main_refresh_layout.finishRefresh()
            }
        }
        main_refresh_layout.setOnRefreshListener {
            handler?.sendEmptyMessageDelayed(1, 500)
        }
    }
}
