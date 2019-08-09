package com.zzt.utilcode

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.view.ViewGroup

import android.widget.ArrayAdapter
import android.content.Intent
import com.zzt.utilcode.activity.ActivityGlide
import com.zzt.utilcode.activity.ActivityScheduledExecutor
import com.zzt.utilcode.activity.ActivityViewToBitmap
import com.zzt.utilcode.view.FeatureView


class MainActivity : AppCompatActivity() {

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        // 设置适配器
        val adapter = CustomArrayAdapter(this@MainActivity, demos)
        main_list.adapter = adapter
        // 添加监听事件
        main_list.setOnItemClickListener { parent, view, position, id ->
            var demo: DemoDetails = parent.adapter.getItem(position) as DemoDetails
            if (demo.activityClass != null) {
                startActivity(
                    Intent(
                        this.applicationContext,
                        demo.activityClass
                    )
                )
            }
        }
    }

    /**
     * 列表数据
     */
    private val demos = arrayOf(
        //图片
        DemoDetails(R.string.util_title_1, R.string.util_title_1, ActivityGlide::class.java),
        DemoDetails(R.string.util_title_2, R.string.util_title_2_desc, ActivityViewToBitmap::class.java),
        DemoDetails(R.string.util_title_3,  ActivityScheduledExecutor::class.java)

    )

}
