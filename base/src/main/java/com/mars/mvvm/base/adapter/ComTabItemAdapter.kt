package com.mars.mvvm.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.mars.mvvm.base.bean.ComTabItemBean
import com.mars.mvvm.base.databinding.ComtabItemBinding
import com.mars.mvvm.base.interfacer.RvOnClickCallBacker


/**
 * @author Mars
 * 通用Tab 适配器
 */
class ComTabItemAdapter(mCtx: Context, dataList: ArrayList<ComTabItemBean>, layoutId: Int, onItemClickCallBacker: RvOnClickCallBacker<ComTabItemBean>)
    : BaseViewApdater<ComTabItemBean, ComtabItemBinding, ComTabItemViewHolder<ComtabItemBinding>>(
    mCtx,
    layoutId,
    onItemClickCallBacker,
    dataList) {

    override fun bindViewHolder(holder: ComTabItemViewHolder<ComtabItemBinding>, itemData: ComTabItemBean, position: Int) {
        setItemViewOnClicker(holder, position)
        holder!!.mDataBinding!!.ctxTv!!.text = dataList!![position].text
        holder!!.mDataBinding!!.ctxTv!!.setTextColor(AppCompatResources.getColorStateList(mCtx!!,
            dataList!![position].textColor))
        val drawable =
            AppCompatResources.getDrawable(mCtx!!, dataList!![position].rid)
        val gravity: Int = dataList!![position].drawableGravity
        holder!!.mDataBinding!!.ctxTv!!.setIconDirection(gravity)
        holder!!.mDataBinding!!.ctxTv!!.setIconNormal(drawable)
    }

    override fun initViewHolderView(mCtx: Context, itemView: ViewGroup): ComTabItemViewHolder<ComtabItemBinding> {
        var view: View = LayoutInflater.from(mCtx).inflate(layoutId, itemView, false)
        return ComTabItemViewHolder(view)
    }
}