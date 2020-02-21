package com.mars.mvvm.business.ui.fragment

import com.mars.mvvm.base.ui.BaseFragment
import com.mars.mvvm.business.R
import com.mars.mvvm.business.databinding.HomeBinding

/**
 * @author Mars
 * 首页Fragment
 */
class HomeFragment : BaseFragment<HomeBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.home
    }

    override fun initData() {
    }

    override fun doWork() {
    }
}