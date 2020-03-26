package com.mars.mvvm.business.adapter.viewholder

import android.view.View
import com.mars.mvvm.base.adapter.BaseViewHolder
import com.mars.mvvm.business.R

/**
 * @author Mars
 * @date 2020-3-26
 *  文章列表ViewHolder
 */
class ArticleViewHolder(itemView: View) :
    BaseViewHolder(itemView) {
    init {
        initView(R.id.topTv)
        initView(R.id.newestTv)
        initView(R.id.updateTimeTv)
    }
}