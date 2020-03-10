package com.mars.mvvm.business.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.mars.mvvm.base.viewmodel.BaseViewModel
import com.mars.mvvm.business.bean.BannerBean
import com.mars.mvvm.business.bean.HomeArticleBean
import com.mars.mvvm.business.repository.HomeRepository
import com.mars.mvvm.common_utils.LogManger
import com.mars.mvvm.network.base.BaseReponseModel

class HomeViewModel(application: Application) : BaseViewModel<HomeRepository>(application) {

    fun logTest() {
        LogManger.logE(TAG, "HomeViewModel logTest（）")
    }

    var mBannerData: MutableLiveData<BaseReponseModel<ArrayList<BannerBean>>> = MutableLiveData()
    var mArticleData: MutableLiveData<BaseReponseModel<HomeArticleBean>> = MutableLiveData()

    /**
     * 获取Banner 数据
     */
    fun getBanner() {
        mRespository.getBanner(mBannerData)
    }

    /**
     * 获取Banner 数据
     */
    fun getNewsetArticle(page: Int) {
        mRespository.getNewsetArticle(page, mArticleData)
    }
}