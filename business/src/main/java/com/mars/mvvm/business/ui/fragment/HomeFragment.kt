package com.mars.mvvm.business.ui.fragment

import android.os.Bundle
import com.mars.mvvm.base.ui.LifeCyclerFragment
import com.mars.mvvm.business.R
import com.mars.mvvm.business.viewmodel.HomeViewModel

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
    }

    override fun dataObserver() {
    }
}

