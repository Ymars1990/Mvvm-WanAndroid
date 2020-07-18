package com.mars.mvvm.base.adapter

import android.util.SparseArray
import android.view.View
import androidx.databinding.ViewDataBinding

import androidx.recyclerview.widget.RecyclerView


/**
 * @author Mars
 * 基类ViewHolder
 */
open class BaseViewHolder(var dataBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {
}