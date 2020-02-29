package com.mars.mvvm.business.viewmodel

import com.mars.mvvm.base.viewmodel.BaseViewModel
import com.mars.mvvm.common_utils.LogManger

class HomeViewModel : BaseViewModel() {

    fun logTest() {
        LogManger.logE(TAG, "HomeViewModel logTest（）")
    }
}