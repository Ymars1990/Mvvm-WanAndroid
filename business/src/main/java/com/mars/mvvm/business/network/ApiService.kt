package com.mars.mvvm.business.network

import android.util.SparseArray
import com.mars.mvvm.business.bean.ArticleBean
import com.mars.mvvm.business.bean.BannerBean
import com.mars.mvvm.business.bean.HomeArticleBean
import com.mars.mvvm.network.base.BaseReponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Mars
 * 请求
 */
interface ApiService {


    /**
     * 获取首页轮播图
     */
    @GET("/banner/json")
    fun getBanner(): Observable<BaseReponseModel<ArrayList<BannerBean>>>

    /**
     * 获取首页最新文章
     */
    @GET("/article/list/{page}/json")
    fun getNewsetArticle(@Path("page") page: Int): Observable<BaseReponseModel<HomeArticleBean>>


}