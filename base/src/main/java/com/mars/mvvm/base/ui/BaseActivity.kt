package com.mars.mvvm.base.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.gyf.immersionbar.ImmersionBar
import com.mars.mvvm.base.R
import com.mars.mvvm.common_utils.LogManger


abstract class BaseActivity : AppCompatActivity() {

    protected var mCtx: Context? = null
    val TAG: String by lazy {
        this.javaClass.simpleName
    }
    var contentFl: FrameLayout? = null
    var statusView: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCtx = this
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_base)
        statusView = findViewById(R.id.statusView)
        contentFl = findViewById(R.id.contentFl)
        contentFl!!.removeAllViews()
        val sonView: View =
            LayoutInflater.from(mCtx).inflate(getLayoutResId(savedInstanceState), contentFl, false)
        contentFl!!.addView(sonView)
        var flp: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
        flp.topMargin = ImmersionBar.getStatusBarHeight(this)
        LogManger.logE(TAG, flp.topMargin)
        contentFl!!.layoutParams = flp
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
