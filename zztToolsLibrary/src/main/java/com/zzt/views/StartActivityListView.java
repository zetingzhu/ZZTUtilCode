package com.zzt.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.zzt.entity.StartActivityDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: zeting
 * @date: 2020/12/24
 * 列表测试数据跳转
 */
public class StartActivityListView extends ListView {
    private List<StartActivityDao> DEMOS = new ArrayList<>();
    private DemoListAdapter demoListAdapter;

    public StartActivityListView(Context context) {
        super(context);
        initView();
    }

    public StartActivityListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public StartActivityListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        // 添加ListItem，设置事件响应
        setAdapter(demoListAdapter = new DemoListAdapter());
        setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View v, int index, long arg3) {
                onListItemClick(index);
            }
        });
//        ColorDrawable sage = new ColorDrawable(this.getResources().getColor(android.R.color.black));
        ColorDrawable sage = new ColorDrawable(Color.parseColor("#ABBAFB"));
        setDivider(sage);
        setDividerHeight(1);
    }

    public void setDemos(StartActivityDao[] str) {
        setDemos(Arrays.asList(str));
    }

    public void setDemos(List<StartActivityDao> list) {
        this.DEMOS = list;
        if (demoListAdapter != null) {
            demoListAdapter.notifyDataSetChanged();
        }
    }


    void onListItemClick(int index) {
        Intent intent;
        intent = new Intent(getContext(), DEMOS.get(index).getActivity());
        getContext().startActivity(intent);
    }

    public class DemoListAdapter extends BaseAdapter {
        @Override
        public View getView(int index, View convertView, ViewGroup parent) {
            MyViewHolder myViewHolder;
            LinearLayout linearLayout = (LinearLayout) convertView;
            if (convertView == null) {
                linearLayout = new LinearLayout(getContext());
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setPadding(20, 10, 20, 10);

                TextView textTitle = new TextView(getContext());
                textTitle.setTextSize(18);
                textTitle.setTextColor(Color.parseColor("#000000"));
                linearLayout.addView(textTitle);

                TextView textDesc = new TextView(getContext());
                textDesc.setTextSize(12);
                textDesc.setTextColor(Color.parseColor("#000000"));
                linearLayout.addView(textDesc);

                myViewHolder = new MyViewHolder(linearLayout, textTitle, textDesc);
                linearLayout.setTag(myViewHolder);
            } else {
                myViewHolder = (MyViewHolder) convertView.getTag();
            }
            myViewHolder.title.setText(DEMOS.get(index).getTitle());
            myViewHolder.desc.setText(DEMOS.get(index).getDescription());
            if (index >= 25) {
                myViewHolder.title.setTextColor(Color.YELLOW);
            }
            return linearLayout;
        }

        @Override
        public int getCount() {
            return DEMOS.size();
        }

        @Override
        public Object getItem(int index) {
            return DEMOS.get(index);
        }

        @Override
        public long getItemId(int id) {
            return id;
        }


        class MyViewHolder {
            TextView title;
            TextView desc;

            public MyViewHolder(LinearLayout linearLayout, TextView textTitle, TextView textDesc) {
                this.title = textTitle;
                this.desc = textDesc;
            }
        }
    }


}
