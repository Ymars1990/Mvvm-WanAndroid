package com.mars.mvvm.common_utils

import android.content.Context
import android.widget.Toast
import com.mars.mvvm.common_utils.MyStringUtils.Companion.stringIsNotNull

/**
 * @author Mars
 * Toast 管理工具类
 */
object ToastManger {
    private var toast: Toast? = null
    @JvmStatic
    fun showToast(ctx: Context?, msg: String?) {
        cancelToast()
        if (toast == null) {
            toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT)
        }
        if (stringIsNotNull(msg)) {
            toast!!.setText(msg)
            toast!!.show()
        }
    }

    fun cancelToast() {
        if (toast != null) {
            toast!!.cancel()
            toast = null
        }
    }
}