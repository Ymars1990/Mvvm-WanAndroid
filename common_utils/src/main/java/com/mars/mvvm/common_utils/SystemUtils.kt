package com.mars.mvvm.common_utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Mars
 * 系统操作工具类
 */
object SystemUtils {
    /**
     * @param activity 设置activity 全屏
     */
    fun setFullScreen(activity: AppCompatActivity) { //全屏
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    /**
     * 跳转到应用设置界面
     *
     * @param context
     */
    fun toSelfSetting(context: AppCompatActivity, requsetCode: Int) {
        val mIntent = Intent()
        //        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            mIntent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
            mIntent.data = Uri.fromParts("package", context.packageName, null)
        } else if (Build.VERSION.SDK_INT <= 8) {
            mIntent.action = Intent.ACTION_VIEW
            mIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails")
            mIntent.putExtra("com.android.settings.ApplicationPkgName", context.packageName)
        }
        context.startActivityForResult(mIntent, requsetCode)
    }

    fun toSelfSetting(context: Context) {
        val mIntent = Intent()
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if (Build.VERSION.SDK_INT >= 9) {
            mIntent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
            mIntent.data = Uri.fromParts("package", context.packageName, null)
        } else if (Build.VERSION.SDK_INT <= 8) {
            mIntent.action = Intent.ACTION_VIEW
            mIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails")
            mIntent.putExtra("com.android.settings.ApplicationPkgName", context.packageName)
        }
        context.startActivity(mIntent)
    }

    /**
     * 跳转到系统设置界面
     */
    fun goSystemSetting(mCtx: Context) {
        val mItent = Intent(Settings.ACTION_SETTINGS)
        mCtx.startActivity(mItent)
    }

    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    fun getStatusBarHeight(context: Context): Int { // 获得状态栏高度
        val resourceId =
            context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return context.resources.getDimensionPixelSize(resourceId)
    }
}