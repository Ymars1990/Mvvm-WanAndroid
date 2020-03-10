package com.mars.mvvm.business.bean

/**
 * @author Mars
 * @description 首页文章列表实体
 */
data class HomeArticleBean(
    var curPage: Int,
    var datas: ArrayList<ArticleBean>,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int
)