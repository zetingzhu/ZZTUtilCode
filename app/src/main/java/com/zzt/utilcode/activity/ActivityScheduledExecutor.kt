package com.zzt.utilcode.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.blankj.utilcode.util.LogUtils
import com.zzt.utilcode.R
import com.zzt.utils.ScheduledExecutorManager
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by zeting
 * Date 2019-08-09.
 */
class ActivityScheduledExecutor : BaseActivity() {

    var mScheduledExecutorManager: ScheduledExecutorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button_list)

        var button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {
//            initView()
//            initView1()
            initView2()
//            initView3()
        }

        var button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            stop()
        }
    }

    private fun initView() {
        mScheduledExecutorManager =
            ScheduledExecutorManager()
        LogUtils.dTag(TAG, "开始执行线程")
        mScheduledExecutorManager!!.scheduleAtFixedRate({
            var sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.getDefault())
            Log.d(TAG, "执行线程时间 1 :" + sdf.format(System.currentTimeMillis()))
            TimeUnit.SECONDS.sleep(5)
        }, 1, 4, TimeUnit.SECONDS)
    }

    private fun initView1() {
        mScheduledExecutorManager = ScheduledExecutorManager()
        LogUtils.dTag(TAG, "开始执行线程")
        mScheduledExecutorManager!!.scheduleAtFixedRate({

            var sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.getDefault())
//            LogUtils.dTag(TAG , "执行线程时间 1 " + sdf.format( System.currentTimeMillis() ) )
            Log.d(TAG, "执行线程时间 1 :" + sdf.format(System.currentTimeMillis()))

            TimeUnit.SECONDS.sleep(5)
        }, 1, 4, TimeUnit.SECONDS)

        mScheduledExecutorManager?.scheduleAtFixedRate({

            var sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.getDefault())
            Log.d(TAG, "执行线程时间 2 :" + sdf.format(System.currentTimeMillis()))

            TimeUnit.SECONDS.sleep(3)
        }, 1, 2, TimeUnit.SECONDS)

    }

    /**
     * initView1 和 initView2 的区别为核心线程只有一个，核心线程只有一个的时候会分别执行两个不同的线程
     */
    private fun initView2() {
        mScheduledExecutorManager =
            ScheduledExecutorManager()
        LogUtils.dTag(TAG, "开始执行线程")
        mScheduledExecutorManager!!.scheduleAtFixedRate({

            var sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.getDefault())
//            LogUtils.dTag(TAG , "执行线程时间 1 " + sdf.format( System.currentTimeMillis() ) )
            Log.d(TAG, "执行线程时间 1 :" + sdf.format(System.currentTimeMillis()))

            TimeUnit.SECONDS.sleep(5)
        }, 1, 4, TimeUnit.SECONDS)

        mScheduledExecutorManager?.scheduleAtFixedRate({

            var sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.getDefault())
            Log.d(TAG, "执行线程时间 2 :" + sdf.format(System.currentTimeMillis()))

            TimeUnit.SECONDS.sleep(3)
        }, 1, 2, TimeUnit.SECONDS)

    }

    private fun initView3() {
        mScheduledExecutorManager =
            ScheduledExecutorManager()
        LogUtils.dTag(TAG, "开始执行线程")

        mScheduledExecutorManager?.scheduleWithFixedDelay({

            var sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.getDefault())
            Log.d(TAG, "执行线程时间 2 :" + sdf.format(System.currentTimeMillis()))

            TimeUnit.SECONDS.sleep(3)
        }, 1, 2, TimeUnit.SECONDS)

    }

    fun stop() {
        LogUtils.dTag(TAG, "关闭 执行线程")
        mScheduledExecutorManager?.stopExecutorService()
    }


}
