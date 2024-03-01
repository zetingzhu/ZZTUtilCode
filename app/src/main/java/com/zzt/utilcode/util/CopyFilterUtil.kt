package com.zzt.utilcode.util

import android.graphics.Color
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zzt.utilcode.R
import com.zzt.views.QMUIFloatLayout

/**
 * @author: zeting
 * @date: 2024/1/5
 * 筛选处理
 */
abstract class CopyFilterUtil<T> {

    abstract fun getShowTextStr(item: T): String
    abstract fun clickItem(selectObj: MutableList<T>)

    companion object {

        const val SELECT_TYPE_SINGLE = 0 // 单选

        const val SELECT_TYPE_MULTIPLE = 1 // 多选
    }

    var mlistData: MutableList<T>? = null

    // 当前选中的数据
    var selectObj: MutableList<T>? = mutableListOf()

    // 适配器单选多选选项
    var selectType: Int = SELECT_TYPE_SINGLE

    /**
     *
     * @param mFloatLayout QMUIFloatLayout?  布局
     * @param mlist MutableList<T>? 所有数据
     * @param select MutableList<CowLabelListObj>? 当前选中数据
     */
    fun setListInLayout(
        mFloatLayout: QMUIFloatLayout?,
        mlist: MutableList<T>?,
        select: MutableList<T>?,
        selectType: Int
    ) {
        this.selectType = selectType
        if (select != null) {
            this.selectObj = select
        }
        this.mlistData = mlist
        if (mlist?.isEmpty() == true) {
            mFloatLayout?.removeAllViews()
        } else {
            if (mlist != null) {
                for ((i, dl) in mlist.withIndex()) {
                    var isSel = false

                    if (select != null) {
                        for (item in select) {
                            if (dl != null) {
                                if (dl.equals(item)) {
                                    isSel = true
                                    break
                                }
                            }
                        }
                    }

                    addItemToFloatLayout(mFloatLayout, i, dl, isSel)
                }
            }
        }
    }

    /**
     * 添加view
     */
    fun addItemToFloatLayout(mFloatLayout: QMUIFloatLayout?, pos: Int, item: T, isSel: Boolean) {
        mFloatLayout?.let {
            val mText = LayoutInflater.from(mFloatLayout.context)
                .inflate(R.layout.copy_filter_item_text, mFloatLayout, false)

            if (mText is TextView) {
                mText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                mText.text = getShowTextStr(item)
                mText.tag = pos
                mText.isSelected = isSel
                mText.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        if (v?.tag != null) {
                            var mPos: Int = v.tag as Int
//                            if (Utils.isNotEmptyDataForList(mlistData, mPos)) {
                            if (v.isSelected) {
                                selectObj?.remove(mlistData!![mPos])
                            } else {
                                if (SELECT_TYPE_SINGLE == selectType) {
                                    selectObj?.clear()
                                }
                                selectObj?.add(mlistData!![mPos])
                            }
                            refreshViewSelect(mFloatLayout)
                            selectObj?.let { it1 -> clickItem(it1) }
//                            }
                        }
                    }
                })
            }
            mFloatLayout.addView(mText)
        }
    }


    /**
     * 刷新所有视图状态
     */
    fun refreshViewSelect(mFloatLayout: QMUIFloatLayout?) {
        if (mFloatLayout != null && selectObj != null) {
            val childCount = mFloatLayout.childCount ?: 0
            for (i in 0 until childCount) {
                val childAt = mFloatLayout.getChildAt(i)
                if (childAt?.tag != null) {
                    var mPos: Int = childAt.tag as Int
                    var booSel = false
                    if (selectObj?.isNotEmpty() == true) {
//                        if (Utils.isNotEmptyDataForList(mlistData, mPos)) {
                        val tagData = mlistData!![mPos]
                        for ((i, v) in selectObj!!.withIndex()) {
                            if (tagData!!.equals(v)) {
                                booSel = true
                                continue
                            }
                        }
//                        }
                    }
                    childAt.isSelected = booSel
                }
            }
        }
    }


    /**
     * 清除选中状态
     */
    fun clearSelectionState(mFloatLayout: QMUIFloatLayout?) {
        mFloatLayout?.let {
            val childCount = mFloatLayout.childCount ?: 0
            for (i in 0 until childCount) {
                val childAt = mFloatLayout.getChildAt(i)
                childAt.isSelected = false
            }
            selectObj?.clear()
        }
    }

}