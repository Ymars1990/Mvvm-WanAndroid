package com.mars.mvvm.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
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
) : BaseRVApdater<ComTabItemBean, ComTabItemViewHolder>(
    mCtx,
    layoutId,
    onItemClickCallBacker,
    dataList
) {
    override fun initViewHolder(parent: ViewGroup, layoutId: Int): ComTabItemViewHolder {
        return ComTabItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(mCtx),
                layoutId,
                parent,
                false
            )
        );
    }

    override fun initData(holder: ComTabItemViewHolder, position: Int) {
        val binding = holder.cdataBinding
        binding.setVariable(2, dataList!![position])
        setItemViewOnClicker(holder, position)
    }

}