package com.mars.mvvm.base.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.gyf.immersionbar.ktx.immersionBar
import com.mars.mvvm.base.R
import com.mars.mvvm.base.databinding.ActivityBaseBinding

abstract class BaseActivity<VDB : ViewDataBinding> : AppCompatActivity() {

    protected var mDataBinding: VDB? = null
    protected var mCtx: Context? = null
    private var baseBinding: ActivityBaseBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCtx = this
        immersionBar {
            statusBarColor(R.color.app_theme_title_color)
            statusBarDarkFont(false)
        }
        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)
        baseBinding!!.contentFl.removeAllViews()
        mDataBinding = DataBindingUtil.inflate(
            getLayoutInflater(),
            getLayoutResId(),
            baseBinding!!.contentFl,
            true
        )
        initData()
        doWork()
    }


    /**
     * 获取当前页面的布局资源ID
     *
     * @return 布局资源ID
     */
    protected abstract fun getLayoutResId(): Int
    /**
     * 初始化数据
     */
    protected abstract fun initData()


    /**
     * @return
     */
    protected abstract fun doWork()
}
