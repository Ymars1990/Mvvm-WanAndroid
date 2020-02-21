package com.mars.mvvm.common_utils

import android.content.Context

/**
 * @author Mars
 * 屏幕方法
 */
object DisPlayUtils {
    /**
     * 获取屏幕宽度
     */
    fun getScreenWidth(ctx: Context): Int {
        val dm = ctx!!.resources.displayMetrics
        return dm.widthPixels
    }

    /**
     * 获取屏幕宽度,设置比例
     */
    fun getScreenWidth(ctx: Context, ratio: Float): Int {
        val dm = ctx!!.resources.displayMetrics
        return (dm.widthPixels * ratio).toInt()
    }

    /**
     * 获取屏幕高度
     */
    fun getScreenHight(ctx: Context): Int {
        val dm = ctx!!.resources.displayMetrics
        return dm.heightPixels
    }

    /**
     * 获取屏幕高度,设置比例
     */
    fun getScreenHight(ctx: Context, ratio: Float): Int {
        val dm = ctx!!.resources.displayMetrics
        return (dm.heightPixels * ratio).toInt()
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     * DisplayMetrics类中属性density
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context!!.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     * DisplayMetrics类中属性density
     */
    fun dip2px(context: Context, dipValue: Float): Int {
        val scale = context!!.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * DisplayMetrics类中属性scaledDensity
     */
    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context!!.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * DisplayMetrics类中属性scaledDensity
     */
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context!!.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }
}