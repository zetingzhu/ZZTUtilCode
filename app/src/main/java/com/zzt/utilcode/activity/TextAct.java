package com.zzt.utilcode.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zzt.utilcode.R;
import com.zzt.utilcode.util.CopyFilterUtil;
import com.zzt.views.QMUIFloatLayout;

import java.util.ArrayList;
import java.util.List;

public class TextAct extends AppCompatActivity {

    QMUIFloatLayout qfl_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        initView();
    }

    private void initView() {
        qfl_layout = findViewById(R.id.qfl_layout);


        CopyFilterUtil<String> mUtil = new CopyFilterUtil<String>() {

            @Override
            public void clickItem(@NonNull List<String> selectObj) {

            }

            @NonNull
            @Override
            public String getShowTextStr(String item) {
                return item;
            }
        };

        List<String> mList = new ArrayList<>();
        mList.add("AAAA");
        mList.add("BBbbb");
        mList.add("cccccc");
        mList.add("cccccc");
        mList.add("DDDDDDDDDDDddddd");
        mList.add("eeeee");
        mList.add("ffffff");
        mList.add("G");
        mList.add("HH");
        mList.add("IIIIIII");
        mList.add("JJ");
        mList.add("KKKKK");
        mUtil.setListInLayout(qfl_layout, mList, null, CopyFilterUtil.SELECT_TYPE_SINGLE);


    }
}