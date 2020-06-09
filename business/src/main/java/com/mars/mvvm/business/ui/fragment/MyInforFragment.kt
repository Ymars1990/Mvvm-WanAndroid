package com.mars.mvvm.business.ui.fragment

import android.os.Bundle
import android.widget.ImageView
import coil.api.load
import com.mars.mvvm.base.ui.BaseFragment
import com.mars.mvvm.business.R

/**
 * @author Mars
 * 公众号Fragment
 */
class MyInforFragment : BaseFragment() {

    var coilLoadIv: ImageView? = null
    override fun getLayoutResId(savedInstanceState: Bundle?): Int {
        return R.layout.myinfor
    }

    override fun initView() {
        coilLoadIv = rootView!!.findViewById(R.id.coilLoadIv)
    }

    override fun initData() {
    }

    override fun doWork() {
        coilLoadIv!!.load("http://img6.16fan.com/attachments/wenzhang/201805/18/152660818127263ge.jpeg") {
//        coilLoadIv!!.load("http://img6.16fan.com/attachments/wenzhang/201805/18/152660818716180ge.jpeg") {
            crossfade(true)
        }
    }
}