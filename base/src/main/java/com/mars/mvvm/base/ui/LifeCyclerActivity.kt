package com.mars.mvvm.base.ui

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mars.mvvm.network.common.DataState
import com.mars.mvvm.base.utils.ClassReflactUtils
import com.mars.mvvm.base.viewmodel.BaseViewModel
import com.mars.mvvm.common_utils.LogManger
import com.mars.mvvm.common_utils.ToastManger


abstract class LifeCyclerActivity<T : BaseViewModel<*>> : BaseActivity() {

    lateinit var mViewModel: T

    var isHasData: Boolean = false

    override fun initData() {
        mViewModel = ViewModelProviders.of(this).get(ClassReflactUtils.getClass(this))
        mViewModel.loadState.observe(this, observer)
        dataObserver()
    }

    /**
     * 正在加载
     */
    open fun dataLoading() {

    }

    /**
     * 加载成功
     */
    open fun dataLoadSuccess() {

    }

    /**
     * 加载失败
     */
    open fun dataLoadFailed() {
        if (isHasData) {

        } else {

        }
    }

    /**
     * 网络异常
     */
    open fun networkError() {
        if (isHasData) {

        } else {

        }
    }

    /**
     * 绑定数据监听
     */
    abstract fun dataObserver()


    private val observer by lazy {
        Observer<DataState> {
            it?.let {
                LogManger.logE(TAG, DataState.DataStateType.toString())
                when (it) {
                    DataState.DataStateType.LOADING -> dataLoading()
                    DataState.DataStateType.SUCCESS -> dataLoadSuccess()
                    DataState.DataStateType.ERROR -> dataLoadFailed()
                    DataState.DataStateType.NETWORK_ERROR -> networkError()
                    DataState.DataStateType.NULL ->
                        ToastManger.showToast(mCtx, DataState.DataStateType.NULL.state)
                    DataState.DataStateType.EMPTY ->
                        ToastManger.showToast(mCtx, DataState.DataStateType.EMPTY.state)
                }
            }
        }
    }
}