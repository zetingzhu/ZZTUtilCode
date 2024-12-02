package com.zzt.views;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.zzt.adapter.StartActivityRecyclerAdapter;
import com.zzt.entity.StartActivityDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zeting
 * @date: 2024/12/2
 * 垂直方向默认列表，带有适配器
 */
public class ZtVerticalRecycleView extends RecyclerView {

    public StartActivityRecyclerAdapter.OnItemClickListener<StartActivityDao> mLis;
    public StartActivityRecyclerAdapter mAdapter;

    public ZtVerticalRecycleView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public ZtVerticalRecycleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ZtVerticalRecycleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        initAdapter();
    }

    /**
     * 初始化适配器
     */
    public void initAdapter() {
        if (isInEditMode()) {
            return;
        }

        mAdapter = StartActivityRecyclerAdapter.setAdapterData(this, RecyclerView.VERTICAL, new ArrayList<>(), mLis);
    }


    public void addListNotifyAdapter(List<StartActivityDao> itemList, StartActivityRecyclerAdapter.OnItemClickListener<StartActivityDao> mLis) {
        if (mAdapter != null) {
            mAdapter.setListener(mLis);
            mAdapter.setDataset(itemList);
            mAdapter.notifyDataSetChanged();
        }
    }
}
