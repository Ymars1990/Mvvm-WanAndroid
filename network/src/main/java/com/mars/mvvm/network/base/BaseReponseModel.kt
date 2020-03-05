package com.mars.mvvm.network.base

/**
 * @author Mars
 * 请求返回实体
 */
open class BaseReponseModel<T>(var errorCode: Int = -1, var errorMsg: String = "", var data: T) {
}