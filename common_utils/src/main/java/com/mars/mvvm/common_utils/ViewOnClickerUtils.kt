package com.mars.mvvm.common_utils

import android.content.Context
import android.view.View
import com.mars.mvvm.common_utils.ToastManger.showToast
import com.mars.mvvm.common_utils.constant.Constant

/**
 * @author Mars
 * 控件点击事件工具类
 */
object ViewOnClickerUtils {
    /**
     * 防止重复点击
     *
     * @param view
     */
    fun doubleOnclickAction(mCtx: Context?, view: View) {
        if (!view.isEnabled) {
            showToast(mCtx, "您的动作太快，我还没反应过来")
        }
        view.isEnabled = false
        view.postDelayed({ view.isEnabled = true }, Constant.DELAYMILLIS)
    }
}