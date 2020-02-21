package com.mars.mvvm.base.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


/**
 * @author Mars
 * 通用tab ViewHolder
 */
class ComTabItemViewHolder<ComtabItemBinding : ViewDataBinding>(itemView: View) :
    BaseViewHolder<ComtabItemBinding>(itemView) {
    init {
        this.mDataBinding = DataBindingUtil.getBinding(this.itemView)
    }
}