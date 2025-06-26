package com.zzt.utilcode

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.zzt.adapter.BtnHorizontalRecyclerAdapter
import com.zzt.adapter.StartActivityRecyclerAdapter
import com.zzt.entity.StartActivityDao
import com.zzt.utilcode.activity.ActivityGlide
import com.zzt.utilcode.activity.ActivityScheduledExecutor
import com.zzt.utilcode.activity.ActivityViewToBitmap
import com.zzt.utilcode.activity.FloatAct
import com.zzt.utilcode.activity.TextAct
import com.zzt.utilcode.view.TextViewRoundClick
import com.zzt.views.ZtHorizontalRecycleView
import com.zzt.views.ZtVerticalRecycleView


class MainActivity : AppCompatActivity() {

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    /**
     * 列表数据
     */
    private var demos = mutableListOf<StartActivityDao>()


    var topListDialog: MutableList<String>? = null
    var topListener: BtnHorizontalRecyclerAdapter.OnItemClickListener<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initTopBtn()
        initData()
    }

    fun initTopBtn() {

        topListDialog = ArrayList()
        topListDialog?.add("初始化")
        topListDialog?.add("替换数据")
        topListDialog?.add("随机数")
        topListDialog?.add("随机颜色")
        topListener = object : BtnHorizontalRecyclerAdapter.OnItemClickListener<String> {
            override fun onItemClick(itemView: View?, position: Int, data: String?) {
                when (position) {

                }
            }
        }
        var rv_list_top = findViewById<ZtHorizontalRecycleView>(R.id.rv_list_top)
        rv_list_top.addListNotifyAdapter(topListDialog, topListener)
    }

    private fun initData() {
        demos = mutableListOf<StartActivityDao>(
            StartActivityDao(
                "流布局，自动排列文本宽度", "文本长短不一根据长度来控制", FloatAct::class.java
            ),
            StartActivityDao(
                "测试页面", "", TextAct::class.java
            ),
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
                resources.getString(R.string.util_title_3), ActivityScheduledExecutor::class.java
            ),
            StartActivityDao(
                "默认跳转位置", "跳到一个只设置布局的 activity 中", R.layout.activity_only_layout
            ),
            StartActivityDao("打开POP窗口，View 下方", "打开一个公共的 PopupWindow", "V1"),
            StartActivityDao("打开POP窗口，View 上方", "打开一个公共的 PopupWindow", "V2"),
            StartActivityDao("打开POP窗口，在屏幕中间 124", "打开一个公共的 PopupWindow", "V3"),

            )
        var main_list = findViewById<ZtVerticalRecycleView>(R.id.main_list)
        // 设置适配器
        main_list.addListNotifyAdapter(
            demos,
            object : StartActivityRecyclerAdapter.OnItemClickListener<StartActivityDao> {
                override fun onItemClick(itemView: View?, position: Int, data: StartActivityDao?) {

                }
            })
    }

    private fun initView() {
        findViewById<TextViewRoundClick>(R.id.btn_sss).setOnClickListener {
            Log.d("TextViewRemoveScroll", "建仓事件触发了")
        }
    }

    fun getContext(): Context {
        return this@MainActivity
    }

    fun dp2px(context: Context, dpValue: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dpValue.toFloat(), context.resources.displayMetrics
        ).toInt()
    }
}
