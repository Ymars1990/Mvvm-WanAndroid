package com.mars.mvvm.base

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.SOURCE)
annotation class AroutePath {
    companion object {
        const val COM_WEBVIEW = "/com/MyWebViewActivity";
    }
}