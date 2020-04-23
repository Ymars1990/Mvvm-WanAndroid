package com.mars.mvvm.business.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mars.mvvm.base.interfacer.RvOnClickCallBacker
import com.mars.mvvm.base.ui.LifeCyclerFragment
import com.mars.mvvm.business.R
import com.mars.mvvm.business.adapter.ArticleAapter
import com.mars.mvvm.business.adapter.BannerImagerAdapter
import com.mars.mvvm.business.bean.ArticleBean
import com.mars.mvvm.business.bean.BannerBean
import com.mars.mvvm.business.bean.HomeArticleBean
import com.mars.mvvm.business.viewmodel.HomeViewModel
import com.mars.mvvm.common_utils.LogManger
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.listener.OnBannerListener


/**
 * @author Mars
 * 首页Fragment
 */
class HomeFragment : LifeCyclerFragment<HomeViewModel>(), OnRefreshLoadMoreListener,
    OnBannerListener<BannerBean>, RvOnClickCallBacker<ArticleBean> {

    var page: Int = 0
    var articleRv: RecyclerView? = null

    var homeBv: Banner<BannerBean, BannerImagerAdapter>? = null

    val banners: ArrayList<BannerBean> = ArrayList()
    val articles: ArrayList<ArticleBean> = ArrayList()
    var article: HomeArticleBean? = null
    override fun getLayoutResId(savedInstanceState: Bundle?): Int {
        return R.layout.home
    }

    override fun initView() {
        homeBv = rootView!!.findViewById(R.id.homeBv)
        baseSrl!!.setOnRefreshLoadMoreListener(this)
        articleRv = rootView!!.findViewById(R.id.articleRv)
        initRv()
    }

    override fun initData() {
        super.initData()
    }

    override fun doWork() {
        initBanner()
        mViewModel.logTest()
        mViewModel.getBanner()
        mViewModel.getNewsetArticle(page)
    }

    private fun initBanner() {
        homeBv!!.adapter = BannerImagerAdapter(parentCtx!!, banners)
        homeBv!!.setOnBannerListener(this)
        homeBv!!.isAutoLoop(true)
        homeBv!!.indicator = CircleIndicator(parentCtx)
    }

    private fun initRv() {
        articleRv!!.layoutManager = LinearLayoutManager(parentCtx)
        articleRv!!.adapter =  ArticleAapter(parentCtx!!, articles, R.layout.article_item, this)
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
        mViewModel.mArticleData.observe(this, Observer { response ->
            response?.let {
                article = it.data
                LogManger.logE(TAG, it.data.toString())
                if (page == 0) {
                    articles.clear()
                }
                if (article!!.datas.size == 20) {
                    page++
                    baseSrl!!.setEnableLoadMore(true);
                } else {
                    baseSrl!!.finishLoadMoreWithNoMoreData();
                }
                articles.addAll(article!!.datas)
                articleRv!!.adapter!!.notifyDataSetChanged()
            }
        })
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        mViewModel.getNewsetArticle(page)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        page = 0
        mViewModel.getBanner()
        mViewModel.getNewsetArticle(page)
    }

    override fun OnBannerClick(data: BannerBean?, position: Int) {
        if (banners.size > 0) {
            LogManger.logE(TAG, String.format("轮播图:%s 被点击", banners[position]))
        }
    }

    override fun onItemClickerCallBacker(view: View?, pos: Int, data: ArticleBean) {

    }
}

