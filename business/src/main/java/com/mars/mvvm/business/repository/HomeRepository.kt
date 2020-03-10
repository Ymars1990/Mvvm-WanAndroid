package com.mars.mvvm.business.repository

import android.util.SparseArray
import androidx.lifecycle.MutableLiveData
import com.mars.mvvm.business.bean.ArticleBean
import com.mars.mvvm.business.bean.BannerBean
import com.mars.mvvm.business.bean.HomeArticleBean
import com.mars.mvvm.business.network.ApiRepository
import com.mars.mvvm.network.base.BaseObserver
import com.mars.mvvm.network.base.BaseReponseModel
import com.mars.mvvm.network.common.DataState
import com.mars.mvvm.network.execute

/**
 * @author Mars
 * Home 请求
 */
class HomeRepository(val loadState: MutableLiveData<DataState>) : ApiRepository() {

    fun getBanner(liveData: MutableLiveData<BaseReponseModel<ArrayList<BannerBean>>>) {
        apiService.getBanner().execute(BaseObserver(liveData, loadState, this))
    }

    fun getNewsetArticle(page:Int,liveData: MutableLiveData<BaseReponseModel<HomeArticleBean>>) {
        apiService.getNewsetArticle(page).execute(BaseObserver(liveData, loadState, this))
    }
}