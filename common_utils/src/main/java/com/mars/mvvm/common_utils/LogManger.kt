package com.mars.mvvm.common_utils

import android.util.Log

/**
 * @author Mars
 * 日志管理工具类
 */
object LogManger {
    /**
     * @param tag TAG
     * @param msg 日志信息
     */
    fun logE(tag: String?, msg: Any?) {
        Log.e(tag, MyStringUtils.transformToString(msg))
    }
    /**
     * @param tag TAG
     * @param msg 日志信息
     */
    fun logI(tag: String?, msg: Any?) {
        Log.i(tag, MyStringUtils.transformToString(msg))
    }

    /**
     * @param tag TAG
     * @param msg 日志信息
     */
    fun logD(tag: String?, msg: Any?) {
        Log.d(tag, MyStringUtils.transformToString(msg))
    }

    /**
     * @param tag TAG
     * @param msg 日志信息
     */
    fun logW(tag: String?, msg: Any?) {
        Log.w(tag, MyStringUtils.transformToString(msg))
    }

    /**
     * @param tag TAG
     * @param msg 日志信息
     */
    fun logV(tag: String?, msg: Any?) {
        Log.v(tag, MyStringUtils.transformToString(msg))
    }

    /**
     * @param tag TAG
     * @param msg 日志信息
     */
    fun logWTF(tag: String?, msg: Any?) {
        Log.wtf(tag, MyStringUtils.transformToString(msg))
    }
}