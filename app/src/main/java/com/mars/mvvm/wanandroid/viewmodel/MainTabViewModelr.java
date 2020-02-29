package com.mars.mvvm.wanandroid.viewmodel;

import android.view.View;

import com.mars.mvvm.base.bean.ComTabItemBean;
import com.mars.mvvm.base.interfacer.RvOnClickCallBacker;
import com.mars.mvvm.base.viewmodel.BaseViewModel;

import org.jetbrains.annotations.Nullable;

/**
 *
 */
public class MainTabViewModelr extends BaseViewModel implements RvOnClickCallBacker<ComTabItemBean> {


    @Override
    public void onItemClickerCallBacker(@Nullable View view, int pos, ComTabItemBean data) {

    }
}
