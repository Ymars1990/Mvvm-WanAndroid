package com.mars.mvvm.base.interfacer

import android.view.View

/**
 * @author Mars
 */
interface RvOnClickCallBacker<T> {
    /**
     * * RecyclerView 点击回调
     *
     * @param view 点击的控件
     * @param pos  点击的位置
     * @param data 点击对象数据
     * @return 不返回
     */
    fun onItemClickerCallBacker(view: View?, pos: Int, data: T)
}