package com.mars.mvvm.base.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar
import com.mars.mvvm.base.AroutePath.Companion.COM_WEBVIEW
import com.mars.mvvm.base.R
import com.mars.mvvm.common_utils.LogManger
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient

@Route(path = COM_WEBVIEW)
class MyWebViewActivity : BaseActivity(), View.OnClickListener {
    var tbsWv: WebView? = null
    var backIv: ImageView? = null
    var leftTv: TextView? = null
    var url: String? = null
    override fun getLayoutResId(savedInstanceState: Bundle?): Int {
        immersionBar {
            statusBarColor(R.color.transparent)
            statusBarDarkFont(true)
        }
        return R.layout.activity_my_webview
    }

    override fun initView() {
        tbsWv = findViewById(R.id.tbsWv)
        backIv = findViewById(R.id.backIv)
        leftTv = findViewById(R.id.leftTv)
        leftTv?.visibility = View.VISIBLE
        backIv!!.setOnClickListener(this)

        initWebView()
    }

    override fun initData() {
        url = intent.getStringExtra("url")
    }

    override fun doWork() {
        load()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.backIv -> {
                finish()
            }
        }
    }

    fun initWebView() {
        tbsWv!!.getSettings().setSupportZoom(true) //支持缩放，默认为true。是下面那个的前提。

        tbsWv!!.getSettings().setBuiltInZoomControls(true) //设置内置的缩放控件。若为false，则该WebView不可缩放

        tbsWv!!.getSettings().setDisplayZoomControls(true) //隐藏原生的缩放控件

        tbsWv!!.getSettings().setBlockNetworkImage(false) //解决图片不显示

        tbsWv!!.getSettings().setLoadsImagesAutomatically(true) //支持自动加载图片

        tbsWv!!.getSettings().setDefaultTextEncodingName("utf-8") //设置编码格式


        //该界面打开更多链接

        //该界面打开更多链接
        tbsWv!!.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(p0: WebView?, p1: String?): Boolean {
                return super.shouldOverrideUrlLoading(p0, p1)
            }
        })
        //监听网页的加载进度
        //监听网页的加载进度
        tbsWv!!.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(p0: WebView?, p1: Int) {
                super.onProgressChanged(p0, p1)
                LogManger.logE(TAG, "监控界面加载的url进度: $p1")
            }

            override fun onReceivedTitle(p0: WebView?, p1: String?) {
                super.onReceivedTitle(p0, p1)
                leftTv?.text = p1
            }
        })
    }

    fun load() {

        tbsWv!!.loadUrl(url)
        LogManger.logE(TAG, "监控界面加载的url为: $url")

    }
}