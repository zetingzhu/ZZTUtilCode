package com.zzt.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author: zeting
 * @date: 2020/12/24
 * 默认有数据的列表view
 */
public class DefaultDataRecycleView extends RecyclerView {
    public DefaultDataRecycleView(@NonNull Context context) {
        super(context);
        initView();
    }

    public DefaultDataRecycleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DefaultDataRecycleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    SparseArray<String> sparseArray = new SparseArray<>();

    private void initView() {
        for (int i = 0; i < 60; i++) {
            sparseArray.put(i, "数据_" + i);
        }
        setLayoutManager(new LinearLayoutManager(getContext()));
        setAdapter(new Adapter() {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                TextView textView = new TextView(parent.getContext());
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
                textView.setLayoutParams(params);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.GRAY);
                return new ScrollViewHolder(textView);
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                ScrollViewHolder mViewHolder = (ScrollViewHolder) holder;
                ((TextView) mViewHolder.itemView).setText(sparseArray.get(position));
                if (position % 2 == 0) {
                    mViewHolder.itemView.setBackgroundColor(Color.parseColor("#eeeeee"));
                } else {
                    mViewHolder.itemView.setBackgroundColor(Color.WHITE);
                }
            }

            @Override
            public int getItemCount() {
                return sparseArray.size();
            }
        });
    }

    class ScrollViewHolder extends ViewHolder {
        public ScrollViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
