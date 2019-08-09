package com.zzt.utilcode

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.zzt.utilcode.view.FeatureView

/**
 * 列表的适配器
 * Created by zeting
 * Date 2019-08-09.
 */
class CustomArrayAdapter(context: Context, demos: Array<DemoDetails>) :
    ArrayAdapter<DemoDetails>(context, R.layout.main_list_feature, R.id.title, demos) {

    /**
     * override fun getView(position: Int, convertView: View , parent: ViewGroup ): View {
     * java.lang.IllegalArgumentException: Parameter specified as non-null is null:
     * method kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull, parameter convertView
     * 这里转换成kotlin 语言后，需要加上空检测机制，不然会报错上面的错误信息
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val featureView: FeatureView
        if (convertView is FeatureView) {
            featureView = convertView as FeatureView
        } else {
            featureView = FeatureView(context)
        }
        val demo = getItem(position)
        featureView.setTitleId(demo.titleId, demo.descriptionId, demo.activityClass != null)
        return featureView
    }
}