package com.zzt.utilcode

/**
 * 列表数据对象
 * Created by zeting
 * Date 2019-08-09.
 */
class DemoDetails {
    var titleId: Int? = null
    var descriptionId: Int? = null
    var activityClass: Class<out android.app.Activity>? = null

    constructor (titleId: Int, activityClass: Class<out android.app.Activity>)   {
        this.titleId = titleId
        this.activityClass = activityClass
    }

    constructor (titleId: Int, descriptionId: Int, activityClass: Class<out android.app.Activity>)   {
        this.titleId = titleId
        this.descriptionId = descriptionId
        this.activityClass = activityClass
    }
}