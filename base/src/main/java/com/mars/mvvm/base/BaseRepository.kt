package com.mars.mvvm.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * @author Mars
 * 发送数据请求
 */
class BaseRepository {
    private val mCompositeSubscription by lazy { CompositeDisposable() }

    fun subscribe(disposable: Disposable) = mCompositeSubscription.add(disposable)

    fun unSubscribe() = mCompositeSubscription.dispose()
}