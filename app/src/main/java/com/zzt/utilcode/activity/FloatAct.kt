package com.zzt.utilcode.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.zzt.utilcode.R
import com.zzt.views.ZFloatLayout

class FloatAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_float)
        init()
    }

    private fun init() {
        var mData = arrayListOf<String>()
        for (i in 0..20) {
            var value = ""
            for (j in 0..i) {
                value = value.plus("_").plus(j.toString())
            }
            mData.add(value)
        }

        var fll_layout = findViewById<ZFloatLayout>(R.id.fll_layout)
        fll_layout.setAdapter(object : ZFloatLayout.FloatAdapter() {
            override fun getCount(): Int {
                return mData.size
            }

            override fun getLayoutID(): Int {
                return R.layout.item_float
            }

            override fun onBindView(v: View, position: Int) {
                v.findViewById<TextView>(R.id.tv_name).text = mData[position]
            }
        })
    }
}