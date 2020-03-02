package com.mars.mvvm.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mars.mvvm.base.BaseRepository
import com.mars.mvvm.base.common.DataState
import com.mars.mvvm.base.utils.ClassReflactUtils

/**
 * @author Mars
 * MVVM  基类ViewModel
 */
abstract class BaseViewModel<T : BaseRepository>(application: Application) :
    AndroidViewModel(application) {
    var TAG: String? = null

    init {
        TAG = this.javaClass.simpleName
    }

    val loadState by lazy { MutableLiveData<DataState>() }

    val mRespository: T by lazy {
        (ClassReflactUtils.getClass<T>(this)).getDeclaredConstructor(MutableLiveData::class.java)
            .newInstance(loadState)
    }

    override fun onCleared() {
        super.onCleared()
        mRespository!!.unSubscribe()
    }

}