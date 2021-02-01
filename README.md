
# android 工具类代码整理

## ByteUtils
byte hex int String 数据处理 --> [ByteUtils][ByteUtils.java]
```
```

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
[ByteUtils.java]: https://github.com/zetingzhu/ZZTUtilCode/blob/master/zztutillibrary/src/main/java/com/zzt/zztutillibrary/ByteUtils.java



gradlew bintrayUpload
Starting a Gradle Daemon (subsequent builds will be faster)
> Task :zztutillibrary:bintrayUpload FAILED

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':zztutillibrary:bintrayUpload'.
> javax.net.ssl.SSLException: Software caused connection abort: recv failed

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.

* Get more help at https://help.gradle.org

BUILD FAILED in 59s
27 actionable tasks: 2 executed, 25 up-to-date

D:\ZZTAndroid\Android_Work\ZZTUtilCode>

* What went wrong:
A problem occurred configuring root project 'ZZTUtilCode'.
> Could not resolve all artifacts for configuration ':classpath'.
   > Could not download maven-artifact-manager.jar (org.apache.maven:maven-artifact-manager:2.2.1)
      > Could not get resource 'https://jcenter.bintray.com/org/apache/maven/maven-artifact-manager/2.2.1/maven-artifact-manager-2.2.1.jar'.
         > Could not GET 'https://jcenter.bintray.com/org/apache/maven/maven-artifact-manager/2.2.1/maven-artifact-manager-2.2.1.jar'.
            > Software caused connection abort: recv failed



D:\ZZTAndroid\Android_Work\ZZTUtilCode>gradlew bintrayUpload
> Task :zztutillibrary:bintrayUpload FAILED
> Task :bintrayPublish FAILED

FAILURE: Build completed with 2 failures.

1: Task failed with an exception.
-----------
* What went wrong:
Execution failed for task ':zztutillibrary:bintrayUpload'.
> Could not create package 'com/zzt/ZZTUtilLibrary': HTTP/1.1 404 Not Found [message:Repo 'zzt' was not found]

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.
==============================================================================

2: Task failed with an exception.
-----------
* What went wrong:
Execution failed for task ':bintrayPublish'.
> Could not publish 'com/zzt/ZZTUtilLibrary/1.0.0': HTTP/1.1 404 Not Found [message:Repo 'zzt' was not found]

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.
==============================================================================

* Get more help at https://help.gradle.org

BUILD FAILED in 19s
28 actionable tasks: 25 executed, 3 up-to-date

D:\ZZTAndroid\Android_Work\ZZTUtilCode>
