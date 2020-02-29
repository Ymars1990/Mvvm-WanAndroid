package com.mars.mvvm.base.viewmodel

import androidx.lifecycle.ViewModel

/**
 * @author Mars
 * MVVM  基类ViewModel
 */
abstract class BaseViewModel : ViewModel() {
    var TAG: String? = null


    override fun onCleared() {
        super.onCleared()
    }

    //重新加载
    open fun reload() {}

    //刷新
    open fun refresh() {}

    //加载更多
    open fun loadMore() {}
}