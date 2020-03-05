package com.mars.mvvm.base.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

import com.gyf.immersionbar.ktx.immersionBar
import com.mars.mvvm.base.R


abstract class BaseActivity : AppCompatActivity() {

    protected var mCtx: Context? = null
    val TAG: String by lazy {
        this.javaClass.simpleName
    }
    var contentFl: FrameLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCtx = this
        immersionBar {
            statusBarColor(R.color.app_theme_title_color)
            statusBarDarkFont(false)
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_base)
        contentFl = findViewById(R.id.contentFl)
        contentFl!!.removeAllViews()
        val sonView: View =
            LayoutInflater.from(mCtx).inflate(getLayoutResId(savedInstanceState), contentFl, false)
        contentFl!!.addView(sonView)
        initView()
        initData()
        doWork()
    }


    override fun onDestroy() {
        super.onDestroy()
    }
    /**
     * 获取当前页面的布局资源ID
     *
     * @return 布局资源ID
     */
    protected abstract fun getLayoutResId(savedInstanceState: Bundle?): Int

    /**
     * 初始化控件
     */
    protected abstract fun initView()

    /**
     * 初始化数据
     */
    protected abstract fun initData()


    /**
     * @return
     */
    protected abstract fun doWork()
}
