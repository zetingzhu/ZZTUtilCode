package com.zzt.utilcode.activity

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.*
import com.zzt.bitmaputil.ViewToBitmapUtil
import com.zzt.utilcode.R
import com.zzt.utilcode.helper.DialogHelper
import java.io.File
import java.util.*

/**
 *
 * Created by zeting
 * Date 2019-08-06.
 */
class ActivityViewToBitmap : AppCompatActivity() {
    companion object {
        val TAG: String = ActivityViewToBitmap::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_to_bitmap)

        requestPermission();

        initView()

    }

    /**
     * 权限申请
     */
    private fun requestPermission() {
        var isGranted = PermissionUtils.isGranted(PermissionConstants.STORAGE)
        LogUtils.dTag(TAG, "查找存储权限状态：$isGranted")
        var isGranted1 = PermissionUtils.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        LogUtils.dTag(TAG, "1查找存储权限状态：$isGranted1")
        if (!isGranted) {
            PermissionUtils.permission(PermissionConstants.STORAGE)
//            PermissionUtils.permission(  Manifest.permission.WRITE_EXTERNAL_STORAGE ,  Manifest.permission.READ_EXTERNAL_STORAGE )
                .rationale(object : PermissionUtils.OnRationaleListener {
                    override fun rationale(
                        p0: UtilsTransActivity,
                        shouldRequest: PermissionUtils.OnRationaleListener.ShouldRequest
                    ) {
                        LogUtils.eTag(TAG, "已经拒绝了申请存储权限")
                        if (shouldRequest != null) {
                            DialogHelper.showRationaleDialog(
                                this@ActivityViewToBitmap,
                                shouldRequest
                            )
                        }
                    }
                }) // 设置拒绝权限后再次请求的回调接口
                .callback(object : PermissionUtils.FullCallback {
                    override fun onGranted(permissionsGranted: MutableList<String>) {
                        LogUtils.dTag(TAG, "同意申请的权限" + permissionsGranted)
                    }

                    override fun onDenied(
                        permissionsDeniedForever: MutableList<String>,
                        permissionsDenied: MutableList<String>
                    ) {
                        LogUtils.dTag(
                            TAG,
                            "拒绝申请的权限" + permissionsDeniedForever,
                            permissionsDenied
                        )
                        if (!permissionsDeniedForever.isEmpty()) {
                            DialogHelper.showOpenAppSettingDialog(this@ActivityViewToBitmap)
                            return
                        }
                    }
                }) // 设置回调
                .request()// 请求申请
        }

    }

    private fun initView() {
        var ll_save_view = findViewById<LinearLayout>(R.id.ll_save_view)
        // 初始化view
        val snapshot =
            ViewToBitmapUtil(ll_save_view)
        val bitmap = snapshot.getBitmap(this@ActivityViewToBitmap, 300, 500)

        LogUtils.dTag(TAG, "bitmap 宽： " + bitmap.width + " - 高：" + bitmap.height)
        LogUtils.dTag(TAG, "bitmap 设置信息： " + bitmap.config.toString())
        LogUtils.dTag(TAG, "bitmap 实际内存空间大小： " + bitmap.byteCount)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LogUtils.dTag(TAG, "bitmap 复用占内存空间大小： " + bitmap.allocationByteCount)
        }

        var iv_save = findViewById<ImageView>(R.id.iv_save)
        // 保存图片到本地
        iv_save.setOnClickListener {
            // 初始化view
            val snapshot =
                ViewToBitmapUtil(ll_save_view)
            // 将view 转换成 bitmap
            val bitmapSrc = snapshot.apply()

            //系统相册目录
            val path =
                File.separator + Environment.DIRECTORY_DCIM + File.separator + "Camera" + File.separator
            var appCacheDir: File? = File(Environment.getExternalStorageDirectory(), path)
            // 保存图片完全路径
            val takePhotoFile = File(appCacheDir, "share_" + System.currentTimeMillis() + ".png")
            LogUtils.dTag(TAG, "保存图片路径： " + takePhotoFile.absolutePath)
            // 图片保存
            val save = ImageUtils.save(bitmapSrc, takePhotoFile, Bitmap.CompressFormat.JPEG)
            LogUtils.dTag(TAG, "保存图片成功状态： " + save)
            ToastUtils.showShort("保存图片状态：" + save)
            // 最后通知图库更新
            this@ActivityViewToBitmap.getApplicationContext()
                .sendBroadcast(
                    Intent(
                        Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.fromFile(takePhotoFile)
                    )
                )

        }

    }


}