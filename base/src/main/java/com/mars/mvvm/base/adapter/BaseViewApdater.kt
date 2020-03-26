package com.mars.mvvm.base.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mars.mvvm.base.interfacer.RvOnClickCallBacker
import com.mars.mvvm.common_utils.ViewOnClickerUtils

abstract class BaseViewApdater<T : Any, VH : BaseViewHolder>(
    mCtx: Context,
    layoutId: Int,
    onItemClickCallBacker: RvOnClickCallBacker<T>?,
    dataList: ArrayList<T>?
) :
    RecyclerView.Adapter<VH>() {
    var TAG: String = this.javaClass.simpleName
    var mCtx: Context? = null
    var layoutId: Int
    var onItemClickCallBacker: RvOnClickCallBacker<T>? = null
    var dataList: ArrayList<T>? = null

    init {
        this.mCtx = mCtx
        this.dataList = dataList
        this.layoutId = layoutId
        this.onItemClickCallBacker = onItemClickCallBacker
    }

    override fun getItemCount(): Int {
        return if (dataList == null) 0 else dataList!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return initViewHolderView(mCtx!!, parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        bindViewHolder(holder!!, dataList!![position], position)
    }

    /**
     * * 绑定数据
     *
     * @param holder
     * @param itemData
     * @param position
     */
    abstract fun bindViewHolder(holder: VH, itemData: T, position: Int)

    /**
     * 初始化控件
     * 调整布局
     *
     * @param mCtx
     */
    abstract fun initViewHolderView(mCtx: Context, parent: ViewGroup): VH


    /**
     * 设置整个Item 点击监听
     *
     * @param holder
     * @param position
     */
    protected fun setItemViewOnClicker(holder: VH, position: Int) {
        holder.itemView.setOnClickListener { v ->
            ViewOnClickerUtils.doubleOnclickAction(mCtx, v)
            onItemClickCallBacker!!.onItemClickerCallBacker(
                v, position, dataList!![position]
            )
        }
    }

}