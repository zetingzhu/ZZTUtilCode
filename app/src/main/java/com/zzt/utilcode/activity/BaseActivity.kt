package com.zzt.utilcode.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import com.zzt.utilcode.MainActivity

/**
 * Created by zeting
 * Date 2019-08-09.
 */
@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(){
    companion object{
        val TAG : String = BaseActivity::class.java.simpleName
    }

}
