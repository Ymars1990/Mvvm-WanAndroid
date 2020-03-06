package com.mars.mvvm.business.bean

/**
 * @author Mars
 * 首页轮播图实体
 */
class BannerBean(
    var id: Int,
    var desc: String,
    var imagePath: String,
    var title: String,
    var url: String
) {
    override fun toString(): String {
        return "BannerBean(id=$id, desc='$desc', imagePath='$imagePath', title='$title', url='$url')"
    }
}