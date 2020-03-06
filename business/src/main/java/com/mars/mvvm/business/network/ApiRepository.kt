package com.mars.mvvm.business.network

import com.mars.mvvm.network.RetrofitManagerFactory
import com.mars.mvvm.network.base.BaseRepository

open class ApiRepository : BaseRepository() {

    public val apiService: ApiService by lazy {
        RetrofitManagerFactory.instance.create(ApiService::class.java)
    }
}