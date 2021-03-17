package com.zzt.utilcode

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.zzt.adapter.StartActivityRecyclerAdapter
import com.zzt.entity.StartActivityDao
import com.zzt.utilcode.activity.ActivityGlide
import com.zzt.utilcode.activity.ActivityScheduledExecutor
import com.zzt.utilcode.activity.ActivityViewToBitmap
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    /**
     * 列表数据
     */
    private var demos = mutableListOf<StartActivityDao>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initView()
    }

    private fun initData() {
        demos = mutableListOf<StartActivityDao>(
            //图片
            StartActivityDao(
                resources.getString(R.string.util_title_1),
                resources.getString(R.string.util_title_1),
                ActivityGlide::class.java
            ),
            StartActivityDao(
                resources.getString(R.string.util_title_2),
                resources.getString(R.string.util_title_2_desc),
                ActivityViewToBitmap::class.java
            ),
            StartActivityDao(
                resources.getString(R.string.util_title_3),
                ActivityScheduledExecutor::class.java
            ),
            StartActivityDao("默认跳转位置", "跳到一个只设置布局的 activity 中", R.layout.activity_only_layout)

        )
    }

    private fun initView() {
        // 设置适配器
        StartActivityRecyclerAdapter.setAdapterData(
            main_list,
            RecyclerView.VERTICAL,
            demos,
            null
        )
    }
}
