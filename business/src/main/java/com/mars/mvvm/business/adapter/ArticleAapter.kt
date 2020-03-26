package com.mars.mvvm.business.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mars.mvvm.base.adapter.BaseViewApdater
import com.mars.mvvm.base.adapter.ComTabItemViewHolder
import com.mars.mvvm.base.interfacer.RvOnClickCallBacker
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
) : BaseViewApdater<ArticleBean, ArticleViewHolder>(
    mCtx,
    layoutId,
    onItemClickCallBacker,
    dataList
) {
    override fun bindViewHolder(holder: ArticleViewHolder, itemData: ArticleBean, position: Int) {

    }

    override fun initViewHolderView(mCtx: Context, parent: ViewGroup): ArticleViewHolder {
        var rootView: View = LayoutInflater.from(mCtx).inflate(layoutId, parent, false)
        return ArticleViewHolder(rootView)
    }
}