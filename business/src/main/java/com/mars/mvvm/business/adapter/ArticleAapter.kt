package com.mars.mvvm.business.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
) : BaseViewApdater<ArticleBean, ArticleViewHolder>(
    mCtx,
    layoutId,
    onItemClickCallBacker,
    dataList
) {
    override fun bindViewHolder(holder: ArticleViewHolder, itemData: ArticleBean, position: Int) {
        setItemViewOnClicker(holder,position)

        holder.getChildView<TextView>(R.id.articleTitleTv).text = itemData.title
        holder.getChildView<TextView>(R.id.authorTv).text = String.format("%s", itemData.author)
        holder.getChildView<TextView>(R.id.catagoryTv).text =
            String.format("%s", itemData.superChapterName)
        holder.getChildView<TextView>(R.id.updateTimeTv).text = itemData.niceDate
        holder.getChildView<ImageView>(R.id.followIv)
            .setImageResource(if (itemData.collect) R.mipmap.focus else R.mipmap.focus_no)
    }

    override fun initViewHolderView(mCtx: Context, parent: ViewGroup): ArticleViewHolder {
        var rootView: View = LayoutInflater.from(mCtx).inflate(layoutId, parent, false)
        return ArticleViewHolder(rootView)
    }
}