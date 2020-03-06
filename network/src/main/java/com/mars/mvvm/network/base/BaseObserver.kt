package com.mars.mvvm.network.base

import androidx.lifecycle.MutableLiveData
import com.mars.mvvm.common_utils.BuildConfig
import com.mars.mvvm.common_utils.LogManger
import com.mars.mvvm.common_utils.constant.Constant
import com.mars.mvvm.network.common.DataState
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @author Mars
 *基类观察者
 */
class BaseObserver<T : BaseReponseModel<*>>(
    private val liveData: MutableLiveData<T>,
    private val loadState: MutableLiveData<DataState>,
    private val repository: BaseRepository
) : Observer<T> {
    var TAG: String? = null

    init {
        TAG = this.javaClass.simpleName
    }

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
        repository.subscribe(d)
    }

    override fun onNext(response: T) {
        when (response.errorCode) {
            Constant.RESPONSE_SUCCESS -> {
                if (response.data is List<*>) {
                    if ((response.data as List<*>).isEmpty()) {
                        loadState.postValue(DataState.DataStateType.EMPTY)
                        return
                    }
                }
                loadState.postValue(DataState.DataStateType.SUCCESS)
                liveData.postValue(response)
            }
            Constant.RESPONSE_NOT_LOGIN -> {
                loadState.postValue(DataState.DataStateType.ERROR_LOGIN_INVAILDATE)
            }
            else -> {
                loadState.postValue(DataState.DataStateType.ERROR)
                liveData.postValue(response)
            }
        }
    }

    override fun onError(e: Throwable) {
        if (BuildConfig.DEBUG) {
            e.message?.let { LogManger.logE(TAG, e) }
        }
        loadState.postValue(DataState.DataStateType.NETWORK_ERROR)
    }


}