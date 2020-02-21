package com.mars.mvvm.base.viewmodel

import androidx.databinding.BaseObservable
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.mars.mvvm.common_utils.LogManger
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : BaseObservable(), DefaultLifecycleObserver {

    /**
     * 1、加载失败或无数据  reload()
     * 2、刷新   refresh()
     * 3、加载更多  loadMore()
     */

    var mCompositeDisposable: CompositeDisposable? = null

    override fun onCreate(owner: LifecycleOwner) {
        LogManger.logE("MVVM ViewModel onCreate", this.javaClass.simpleName)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        LogManger.logE("MVVM ViewModel onDestroy", this.javaClass.simpleName)
        mCompositeDisposable!!.clear()
    }

    /**
     * 添加订阅事件
     *
     * @param disposable 订阅事件
     */
    protected fun addDisposable(disposable: Disposable?) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable!!.add(disposable!!)
    }

    //重新加载
    abstract fun reload()

    //刷新
    abstract fun refresh()

    //加载更多
    abstract fun loadMore()
}