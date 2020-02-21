package com.mars.mvvm.base.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


/**
 * @author Mars
 * 基类ViewHolder
 */
abstract class BaseViewHolder<VDB : ViewDataBinding>(parent: View) :
    RecyclerView.ViewHolder(parent) {
    var mDataBinding: VDB? = null
}