package com.mars.mvvm.business.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.gyf.immersionbar.ktx.immersionBar
import com.mars.mvvm.base.ui.LifeCyclerFragment
import com.mars.mvvm.business.R
import com.mars.mvvm.business.adapter.BannerImagerAdapter
import com.mars.mvvm.business.bean.BannerBean
import com.mars.mvvm.business.viewmodel.HomeViewModel
import com.mars.mvvm.common_utils.LogManger
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator


/**
 * @author Mars
 * 首页Fragment
 */
class HomeFragment : LifeCyclerFragment<HomeViewModel>(), OnRefreshLoadMoreListener {

    var page: Int = 0
    var homeBv: Banner<BannerBean, BannerImagerAdapter>? = null

    val banners: ArrayList<BannerBean> = ArrayList()
    override fun getLayoutResId(savedInstanceState: Bundle?): Int {
        return R.layout.home
    }

    override fun initView() {
        homeBv = rootView!!.findViewById(R.id.homeBv)

  /*      var vlp = ViewPager.LayoutParams()
        baseSrl!!.layoutParams = vlp*/
        baseSrl!!.setOnRefreshLoadMoreListener(this)

        immersionBar {
            statusBarColor(R.color.transparent)
            statusBarDarkFont(false)
        }

    }

    override fun initData() {
        super.initData()

    }

    override fun doWork() {
        initBanner()
        mViewModel!!.logTest()
        mViewModel.getBanner()
        mViewModel.getNewsetArticle(page)
    }

    fun initBanner() {
        homeBv!!.adapter = BannerImagerAdapter(banners)
        homeBv!!.indicator = CircleIndicator(parentCtx)
    }

    override fun dataObserver() {
        mViewModel.mBannerData.observe(this, Observer { response ->
            response?.let {
                banners.clear()
                banners.addAll(it.data)
                LogManger.logE(TAG, it.data.toString())
                homeBv!!.setDatas(banners)
            }
        })
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        mViewModel.getNewsetArticle(page)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mViewModel.getBanner()
        mViewModel.getNewsetArticle(page)
    }
}

