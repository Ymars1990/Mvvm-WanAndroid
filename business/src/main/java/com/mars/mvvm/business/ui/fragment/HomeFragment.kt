package com.mars.mvvm.business.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import com.mars.mvvm.base.ui.LifeCyclerFragment
import com.mars.mvvm.business.R
import com.mars.mvvm.business.viewmodel.HomeViewModel
import com.mars.mvvm.common_utils.LogManger


/**
 * @author Mars
 * 首页Fragment
 */
class HomeFragment : LifeCyclerFragment<HomeViewModel>() {


    override fun getLayoutResId(savedInstanceState: Bundle?): Int {
        return R.layout.home
    }

    override fun initView() {
    }

    override fun initData() {
        super.initData()
    }

    override fun doWork() {
        mViewModel!!.logTest()
        mViewModel.getBanner()
    }

    override fun dataObserver() {
        mViewModel.mBannerData.observe(this, Observer { response ->
            response?.let {
                LogManger.logE(TAG, it.data.toString())
            }
        })
    }
}

