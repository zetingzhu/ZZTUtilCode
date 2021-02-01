package com.zzt.entity;

/**
 * @author: zeting
 * @date: 2021/1/4
 * 跳转到 activity 的列表对象
 */
public class StartActivityDao {
    String title;
    String description;
    Class<?> activity;
    private String arouter;// 路由地址

    public StartActivityDao(String title, String description, String arouter) {
        this.title = title;
        this.description = description;
        this.arouter = arouter;
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
}
