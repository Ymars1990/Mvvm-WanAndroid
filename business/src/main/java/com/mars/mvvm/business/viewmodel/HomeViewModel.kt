package com.mars.mvvm.business.viewmodel

import android.app.Application
import com.mars.mvvm.base.viewmodel.BaseViewModel
import com.mars.mvvm.business.repository.HomeRepository
import com.mars.mvvm.common_utils.LogManger

class HomeViewModel(application: Application) : BaseViewModel<HomeRepository>(application) {

    fun logTest() {
        LogManger.logE(TAG, "HomeViewModel logTest（）")
    }


    /**
     * 获取Banner 数据
     */

}