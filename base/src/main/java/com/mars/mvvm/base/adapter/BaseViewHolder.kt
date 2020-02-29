package com.mars.mvvm.base.adapter

import android.util.SparseArray
import android.view.View

import androidx.recyclerview.widget.RecyclerView


/**
 * @author Mars
 * 基类ViewHolder
 */
open class BaseViewHolder(parent: View) :
    RecyclerView.ViewHolder(parent) {
    val mViews: SparseArray<View> = SparseArray()


    /**
     * @param viewId 传入控件ID  即完成Holder View 初始化
     */
    open fun initView(vararg viewId: Int) {
        for (i in 0 until viewId.size) {
            findViewById<View>(viewId[i])
        }
    }

    private fun <T : View?> findViewById(viewId: Int): T? {
        var view: View? = mViews.get(viewId)
        if (view == null) {
            view = itemView.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view as T?
    }

    /**
     * @param viewId 传入控件ID  获取控件
     */
    open fun <T : View?> getChildView(viewId: Int): T {
        return mViews[viewId] as T
    }
}