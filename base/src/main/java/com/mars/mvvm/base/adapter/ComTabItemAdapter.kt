package com.mars.mvvm.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.mars.mvvm.base.R
import com.mars.mvvm.base.bean.ComTabItemBean
import com.mars.mvvm.base.interfacer.RvOnClickCallBacker
import com.mars.mvvm.component.view.DrawableTextView


/**
 * @author Mars
 * 通用Tab 适配器
 */
class ComTabItemAdapter(
    mCtx: Context,
    dataList: ArrayList<ComTabItemBean>,
    layoutId: Int,
    onItemClickCallBacker: RvOnClickCallBacker<ComTabItemBean>
) : BaseViewApdater<ComTabItemBean, ComTabItemViewHolder>(
    mCtx,
    layoutId,
    onItemClickCallBacker,
    dataList
) {

    override fun bindViewHolder(
        holder: ComTabItemViewHolder,
        itemData: ComTabItemBean,
        position: Int
    ) {
        setItemViewOnClicker(holder, position)

        holder!!.getChildView<DrawableTextView>(R.id.ctxTv)!!.text = dataList!![position].text
        holder!!.getChildView<DrawableTextView>(R.id.ctxTv)!!.setTextColor(
            AppCompatResources.getColorStateList(
                mCtx!!,
                dataList!![position].textColor
            )
        )
        val drawable =
            AppCompatResources.getDrawable(mCtx!!, dataList!![position].rid)
        val gravity: Int = dataList!![position].drawableGravity
        holder!!.getChildView<DrawableTextView>(R.id.ctxTv)!!.setIconDirection(gravity)
        holder!!.getChildView<DrawableTextView>(R.id.ctxTv)!!.setIconNormal(drawable)
    }

    override fun initViewHolderView(mCtx: Context, parent: ViewGroup): ComTabItemViewHolder {
        var rootView: View = LayoutInflater.from(mCtx).inflate(layoutId, parent, false)
        return ComTabItemViewHolder(rootView)
    }
}