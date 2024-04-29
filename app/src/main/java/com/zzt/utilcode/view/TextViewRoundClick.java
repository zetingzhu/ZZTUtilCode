package com.zzt.utilcode.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author: zeting
 * @date: 2024/4/2
 * 移除点击事件滑动监听.只处理固定圆点内点击
 */
public class TextViewRoundClick extends AppCompatTextView {
    private static final String TAG = TextViewRoundClick.class.getSimpleName();

    public TextViewRoundClick(@NonNull Context context) {
        super(context);
    }

    public TextViewRoundClick(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewRoundClick(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    float downX = 0;
    float downY = 0;
    float upX = 0;
    float upY = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                upX = event.getX();
                upY = event.getY();
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);
    }

    @Override
    public boolean performClick() {
        Log.d(TAG, "触发点击事件  downX：" + downX + " downY:" + downY + " upX:" + upX + " upY:" + upY);
        return super.performClick();
    }
}
