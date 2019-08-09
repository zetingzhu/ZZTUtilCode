
# android 工具类代码整理

## ViewToBitmapUtil
将view 根据内存大小来转换成bitmap --> [ViewToBitmapUtil][viewtobitmaputil.java]
```
使用方法
    // 将view转换成图片，保存图片到本地
    iv_save.setOnClickListener {
        // 初始化view
        val snapshot = ViewToBitmapUtil(ll_save_view)
        // 将view 转换成 bitmap
        val bitmapSrc  = snapshot.apply()

        //系统相册目录
        val path = File.separator + Environment.DIRECTORY_DCIM + File.separator + "Camera" + File.separator
        var appCacheDir: File? = File(Environment.getExternalStorageDirectory(), path)
        // 保存图片完全路径
        val takePhotoFile = File(appCacheDir, "share_" + System.currentTimeMillis() + ".png")
        LogUtils.dTag(TAG, "保存图片路径： " +  takePhotoFile.absolutePath )
        // 图片保存
        val save = ImageUtils.save( bitmapSrc , takePhotoFile , Bitmap.CompressFormat.JPEG)
        LogUtils.dTag(TAG, "保存图片成功状态： " +  save )
        ToastUtils.showShort("保存图片状态：" + save )
        // 最后通知图库更新
        this@ActivityViewToBitmap.getApplicationContext()
            .sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(takePhotoFile)))

    }

```

## ScheduledExecutorManager
线程池定时器 --> [ScheduledExecutorManager][ScheduledExecutorManager.java]
```
使用方法

    mScheduledExecutorManager = ScheduledExecutorManager()
    mScheduledExecutorManager?.scheduleAtFixedRate({
        var sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.getDefault() )
        Log.d(TAG , "执行线程时间 1 :" + sdf.format( System.currentTimeMillis() ) )
        TimeUnit.SECONDS.sleep(5 )
    }, 1 , 4 , TimeUnit.SECONDS )

```


[viewtobitmaputil.java]: https://github.com/zetingzhu/ZZTUtilCode/blob/master/zztutillibrary/src/main/java/com/zzt/zztutillibrary/ViewToBitmapUtil.java
[ScheduledExecutorManager.java]: https://github.com/zetingzhu/ZZTUtilCode/blob/master/zztutillibrary/src/main/java/com/zzt/zztutillibrary/ScheduledExecutorManager.java

