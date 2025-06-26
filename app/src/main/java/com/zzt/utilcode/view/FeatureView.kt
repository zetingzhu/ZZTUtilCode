package com.zzt.utilcode.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.zzt.utilcode.R

/**
 * Created by zeting
 * Date 2019-07-31.
 */
class FeatureView(context: Context) : FrameLayout(context) {
    var title: android.widget.TextView? = null

    init {
        val layoutInflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        layoutInflater.inflate(R.layout.main_list_feature, this)
        title = findViewById(R.id.title)
    }

    @Synchronized
    fun setTitleId(titleId: Int) {
        title?.setText(titleId)
    }

    @SuppressLint("SetTextI18n")
    @Synchronized
    fun setTitleId(titleId: Int, issub: Boolean) {
        val titleStr = this.resources.getString(titleId)
        if (issub) {
            title?.text = "         $titleStr"
        } else {
            title?.text = titleStr
        }

    }

    @SuppressLint("SetTextI18n")
    @Synchronized
    fun setTitleId(titleId: Int?, descId: Int?, issub: Boolean?) {
        val titleStr = this.resources.getString(titleId!!)
        var descStr = ""
        if (descId != null) {
            descStr = this.resources.getString(descId!!)
        }
        if (issub!!) {
            if (descStr.isNotEmpty()) {
                title?.text = "$titleStr \n  $descStr"
            } else {
                title?.text = titleStr
            }
        } else {
            title?.text = titleStr
        }

    }
}
