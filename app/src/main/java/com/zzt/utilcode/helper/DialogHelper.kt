package com.zzt.utilcode.helper

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.blankj.utilcode.util.PermissionUtils
import com.zzt.utilcode.R

/**
 *
 * Created by zeting
 * Date 2019-08-06.
 */
object DialogHelper {

    fun showRationaleDialog( con : Context , shouldRequest: PermissionUtils.OnRationaleListener.ShouldRequest) {
        AlertDialog.Builder(con)
            .setTitle(android.R.string.dialog_alert_title)
            .setMessage(R.string.permission_rationale_message)
            .setPositiveButton(android.R.string.ok) { dialog, which -> shouldRequest.again(true) }
            .setNegativeButton(android.R.string.cancel) { dialog, which -> shouldRequest.again(false) }
            .setCancelable(false)
            .create()
            .show()
    }

    fun showOpenAppSettingDialog(con : Context ) {
        AlertDialog.Builder(con)
            .setTitle(android.R.string.dialog_alert_title)
            .setMessage(R.string.permission_denied_forever_message)
            .setPositiveButton(android.R.string.ok) { dialog, which -> PermissionUtils.launchAppDetailsSettings() }
            .setNegativeButton(android.R.string.cancel) { dialog, which -> }
            .setCancelable(false)
            .create()
            .show()
    }

}