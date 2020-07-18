package com.mars.mvvm.business.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.mars.mvvm.base.adapter.BaseRVApdater
import com.mars.mvvm.base.adapter.BaseViewApdater
import com.mars.mvvm.base.interfacer.RvOnClickCallBacker
import com.mars.mvvm.business.R
import com.mars.mvvm.business.adapter.viewholder.ArticleViewHolder
import com.mars.mvvm.business.bean.ArticleBean

/**
 * @author Mars
 * 文章适配器
 */
class ArticleAapter(
    mCtx: Context,
    dataList: ArrayList<ArticleBean>,
    layoutId: Int,
    onItemClickCallBacker: RvOnClickCallBacker<ArticleBean>
) : BaseRVApdater<ArticleBean, ArticleViewHolder>(
    mCtx,
    layoutId,
    onItemClickCallBacker,
    dataList
) {
    override fun initViewHolder(parent: ViewGroup, layoutId: Int): ArticleViewHolder {
        return ArticleViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(mCtx),
                layoutId,
                parent,
                false
            )
        )
    }

    override fun initData(holder: ArticleViewHolder, position: Int) {
        val binding = holder.adataBinding
        binding.setVariable(1, dataList!![position])
        setItemViewOnClicker(holder,position)
    }
}