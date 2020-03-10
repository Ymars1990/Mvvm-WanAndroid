package com.mars.mvvm.wanandroid.ui

import android.app.Application
import com.mars.mvvm.wanandroid.R
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader


class MainApplication : Application() {

    companion object {
        init {
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator(DefaultRefreshHeaderCreator { context, layout ->
                layout.setPrimaryColorsId(
                    R.color.app_theme_title_color,
                    R.color.white
                )
                ClassicsHeader(context)
            })
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator(DefaultRefreshFooterCreator() { context, layout ->
                layout.setPrimaryColorsId(
                    R.color.app_theme_title_color,
                    R.color.white
                )
                ClassicsFooter(context).setDrawableSize(20f)
            })
        }
    }
}