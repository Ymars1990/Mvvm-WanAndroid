package com.mars.mvvm.network.common

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy


@DataState.DataStateType
class DataState(val code: Int, val state: String) {

    @Retention(RetentionPolicy.SOURCE)
    annotation class DataStateType {
        companion object {
            var ERROR = DataState(0, "获取数据失败")
            var SUCCESS = DataState(1, "获取数据成功")
            var NETWORK_ERROR = DataState(2, "网络异常")
            var LOADING = DataState(3, "数据加载中")
            var NULL = DataState(4, "数据异常")
            var EMPTY = DataState(5, "无更多数据")
            var ERROR_LOGIN_INVAILDATE = DataState(6, "登录信息过期")
        }
    }

    override fun toString(): String {
        return "DataState(code=$code, state='$state')"
    }

}