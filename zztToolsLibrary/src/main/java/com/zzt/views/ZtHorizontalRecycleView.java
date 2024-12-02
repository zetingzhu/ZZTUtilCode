package com.zzt.views;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.zzt.adapter.BtnHorizontalRecyclerAdapter;
import com.zzt.adapter.StartActivityRecyclerAdapter;
import com.zzt.entity.StartActivityDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zeting
 * @date: 2024/12/2
 * 垂直方向默认列表，带有适配器
 */
public class ZtHorizontalRecycleView extends RecyclerView {

    public StartActivityRecyclerAdapter.OnItemClickListener<StartActivityDao> mLis;
    public BtnHorizontalRecyclerAdapter mAdapter;

    public ZtHorizontalRecycleView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public ZtHorizontalRecycleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ZtHorizontalRecycleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        mAdapter = BtnHorizontalRecyclerAdapter.setAdapterData(this, new ArrayList<>(), null);
    }


    public void addListNotifyAdapter(List<String> itemList, BtnHorizontalRecyclerAdapter.OnItemClickListener<String> mLis) {
        if (mAdapter != null) {
            mAdapter.setListener(mLis);
            mAdapter.setDataset(itemList);
            mAdapter.notifyDataSetChanged();
        }
    }
}
