package com.mars.mvvm.wanandroid.ui

import android.os.Bundle
import android.util.SparseArray
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.gyf.immersionbar.ktx.immersionBar
import com.mars.mvvm.base.adapter.ComTabItemAdapter
import com.mars.mvvm.base.adapter.ComViewPagerAdapter
import com.mars.mvvm.base.bean.ComTabItemBean
import com.mars.mvvm.base.databinding.ActivityBaseBinding
import com.mars.mvvm.base.interfacer.RvOnClickCallBacker
import com.mars.mvvm.base.ui.BaseActivity
import com.mars.mvvm.business.ui.fragment.HomeFragment
import com.mars.mvvm.business.ui.fragment.MyInforFragment
import com.mars.mvvm.business.ui.fragment.NavigationFragment
import com.mars.mvvm.business.ui.fragment.WxarticleFragment
import com.mars.mvvm.common_utils.LogManger
import com.mars.mvvm.component.view.DrawableTextView
import com.mars.mvvm.wanandroid.R

class MainActivity : BaseActivity<ActivityBaseBinding>(), RvOnClickCallBacker<ComTabItemBean>,
    ViewPager.OnPageChangeListener {
    var tabs: ArrayList<ComTabItemBean>? = null
    private val fragments: SparseArray<Fragment> = SparseArray()
    var homeFragment: HomeFragment? = null
    var wxFragment: WxarticleFragment? = null
    var navigationFragment: NavigationFragment? = null
    var myInforFragment: MyInforFragment? = null


    var mainTabRv: RecyclerView? = null
    var contentVp2: ViewPager? = null
    private val getTabSelectedRid = intArrayOf(
        R.mipmap.home02,
        R.mipmap.wechat02,
        R.mipmap.app_logo,
        R.mipmap.app_logo,
        R.mipmap.app_logo
    )
    private val getTabUnSelectedRid = intArrayOf(
        R.mipmap.home01,
        R.mipmap.wechat01,
        R.mipmap.app_logo,
        R.mipmap.app_logo,
        R.mipmap.app_logo
    )

    var tabSelected = 0
    override fun getLayoutResId(savedInstanceState: Bundle?): Int {
        immersionBar {
            statusBarColor(R.color.transparent)
            statusBarDarkFont(true)
        }
        return R.layout.activity_main
    }

    override fun initView() {
        mainTabRv = findViewById(R.id.mainTabRv);
        contentVp2 = findViewById(R.id.contentVp2);
    }

    override fun initData() {
        tabs = ArrayList()
        tabs!!.add(
            ComTabItemBean(
                mCtx!!.resources.getString(R.string.home),
                getTabSelectedRid[0],
                R.color.app_theme_title_color,

                DrawableTextView.ICON_DIR_TOP
            )
        )
        tabs!!.add(
            ComTabItemBean(
                mCtx!!.resources.getString(R.string.wechat_article),
                getTabUnSelectedRid[1],
                R.color.gray,
                DrawableTextView.ICON_DIR_TOP
            )
        )
        tabs!!.add(
            ComTabItemBean(
                mCtx!!.resources.getString(R.string.navigation),
                getTabUnSelectedRid[3],
                R.color.gray,
                DrawableTextView.ICON_DIR_TOP
            )
        )
        tabs!!.add(
            ComTabItemBean(
                mCtx!!.resources.getString(R.string.classify_proj),
                getTabUnSelectedRid[4],
                R.color.gray,
                DrawableTextView.ICON_DIR_TOP
            )
        )

        mainTabRv!!.layoutManager = GridLayoutManager(mCtx, tabs!!.size)
        mainTabRv!!.adapter =
            ComTabItemAdapter(mCtx!!, tabs!!, R.layout.comtab_item, this)

        homeFragment = HomeFragment()
        fragments.put(0, homeFragment!!)
        wxFragment = WxarticleFragment()
        fragments.put(1, wxFragment!!)
        navigationFragment = NavigationFragment()
        fragments.put(2, navigationFragment!!)
        myInforFragment = MyInforFragment()
        fragments.put(3, myInforFragment!!)

        contentVp2!!.adapter = ComViewPagerAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            fragments!!
        )
        contentVp2!!.addOnPageChangeListener(this)
    }

    override fun doWork() {

    }

    override fun onItemClickerCallBacker(view: View?, pos: Int, data: ComTabItemBean) {
        LogManger.logE("Tab", data.toString())
        tabSelected = pos
        resetTab()
    }

    private fun resetTab() {
        for (i in tabs!!.indices) {
            if (tabSelected == i) {
                tabs!![i].rid = getTabSelectedRid[i]
                tabs!![i].textColor = R.color.app_theme_title_color
            } else {
                tabs!![i].rid = getTabUnSelectedRid[i]
                tabs!![i].textColor = R.color.gray
            }
        }
        mainTabRv!!.adapter!!.notifyDataSetChanged()
        contentVp2!!.currentItem = tabSelected
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        tabSelected = position
        resetTab()
    }
}
