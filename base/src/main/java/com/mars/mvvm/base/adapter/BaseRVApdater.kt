package com.mars.mvvm.base.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mars.mvvm.base.interfacer.RvOnClickCallBacker
import com.mars.mvvm.common_utils.ViewOnClickerUtils

abstract class BaseRVApdater<T : Any, VH : BaseViewHolder>(
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
        return initViewHolder(parent, layoutId)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        initData(holder, position)
    }


    /**
     * * 初始化绑定
     *
     * @param holder
     * @param itemData
     * @param position
     */
    abstract fun initViewHolder(parent: ViewGroup, layoutId: Int): VH

    /**
     * 初始化数据
     * 调整布局
     *
     * @param mCtx
     */
    abstract fun initData(holder: VH, position: Int)


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