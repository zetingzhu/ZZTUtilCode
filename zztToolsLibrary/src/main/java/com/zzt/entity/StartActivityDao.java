package com.zzt.entity;

import com.zzt.activity.DefaultActivity;

/**
 * @author: zeting
 * @date: 2021/1/4
 * 跳转到 activity 的列表对象
 */
public class StartActivityDao {
    String title;
    String description;
    Class<?> activity; // 跳转class
    int contentViewId;// 跳转的布局地址
    boolean isDefaultClass;// 是否跳转默认class
    private String arouter;// 路由地址

    public StartActivityDao(String title, String description, String arouter) {
        this.title = title;
        this.description = description;
        this.arouter = arouter;
    }

    public StartActivityDao(String title, String description, int contentViewId) {
        this.title = title;
        this.description = description;
        this.contentViewId = contentViewId;
        this.activity = DefaultActivity.class;
        this.isDefaultClass = true;
    }

    public StartActivityDao(String title, Class<?> activity) {
        this.title = title;
        this.activity = activity;
    }

    public StartActivityDao(String title, String description, Class<?> activity) {
        this.title = title;
        this.description = description;
        this.activity = activity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Class<?> getActivity() {
        return activity;
    }

    public void setActivity(Class<?> activity) {
        this.activity = activity;
    }

    public String getArouter() {
        return arouter;
    }

    public void setArouter(String arouter) {
        this.arouter = arouter;
    }

    public int getContentViewId() {
        return contentViewId;
    }

    public void setContentViewId(int contentViewId) {
        this.contentViewId = contentViewId;
    }

    public boolean isDefaultClass() {
        return isDefaultClass;
    }

    public void setDefaultClass(boolean defaultClass) {
        isDefaultClass = defaultClass;
    }
}
