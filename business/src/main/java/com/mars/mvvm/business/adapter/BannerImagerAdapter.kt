package com.mars.mvvm.business.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mars.mvvm.business.bean.BannerBean
import com.youth.banner.adapter.BannerAdapter


class BannerImagerAdapter(mCtx: Context, data: ArrayList<BannerBean>) :
    BannerAdapter<BannerBean, BannerImagerAdapter.BannerViewHolder>(data) {
    var mCtx: Context? = null

    init {
        this.mCtx = mCtx
    }

    class BannerViewHolder(view: ImageView) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view
    }

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent!!.context)
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
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
        Glide.with(mCtx!!).load(data!!.imagePath).into(holder!!.imageView)
    }
}