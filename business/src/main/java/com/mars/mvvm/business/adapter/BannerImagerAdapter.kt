package com.mars.mvvm.business.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.mars.mvvm.business.bean.BannerBean
import com.mars.mvvm.common_utils.DisPlayUtils
import com.youth.banner.adapter.BannerAdapter


class BannerImagerAdapter(data: ArrayList<BannerBean>) :
    BannerAdapter<BannerBean, BannerImagerAdapter.BannerViewHolder>(data) {


    class BannerViewHolder(view: ImageView) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView? = null
    }

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent!!.context)
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            DisPlayUtils.dip2px(parent.context, 50f)
        )
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        return BannerViewHolder(imageView)
    }

    override fun onBindView(
        holder: BannerViewHolder?,
        data: BannerBean?,
        position: Int,
        size: Int
    ) {

    }
}