package com.mars.mvvm.business.network

import com.mars.mvvm.business.bean.BannerBean
import com.mars.mvvm.network.base.BaseReponseModel
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @author Mars
 * 请求
 */
interface ApiService {


    /**
     * 获取首页轮播图
     */
    @GET("/banner/json")
    fun getBanner(): Observable<BaseReponseModel<List<BannerBean>>>


}