package com.zzt.utilcode

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.zzt.adapter.BtnHorizontalRecyclerAdapter
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
//        initData()
//        initView()
        initDataBtn()
    }

    private fun initDataBtn() {
        var btnList = mutableListOf<String>()
        btnList.add("第一个按钮")
        btnList.add("第2个按钮")
        btnList.add("第三个按钮")
        btnList.add("第4个按钮")
        btnList.add("第五个按钮")

        BtnHorizontalRecyclerAdapter.setAdapterData(
            main_list, btnList
        ) { itemView, position, data ->
            when (position) {
                0 -> {
                    Log.d(TAG, "点击了 0 ：$data")
                }
                1 -> {
                    Log.d(TAG, "点击了 1 ：$data")
                }
                else -> {
                    Log.d(TAG, "点击了 其他 ：$data")
                }
            }
        }
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
            StartActivityDao("默认跳转位置", "跳到一个只设置布局的 activity 中", R.layout.activity_only_layout),
            StartActivityDao("打开POP窗口，View 下方", "打开一个公共的 PopupWindow", "V1"),
            StartActivityDao("打开POP窗口，View 上方", "打开一个公共的 PopupWindow", "V2"),
            StartActivityDao("打开POP窗口，在屏幕中间 124", "打开一个公共的 PopupWindow", "V3")
        )
    }

    private fun initView() {
        // 设置适配器
        StartActivityRecyclerAdapter.setAdapterData(
            main_list,
            RecyclerView.VERTICAL,
            demos
        ) { itemView, position, data ->

        }
    }

    fun getContext(): Context {
        return this@MainActivity
    }

    fun dp2px(context: Context, dpValue: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dpValue.toFloat(),
            context.resources.displayMetrics
        )
            .toInt()
    }


}
