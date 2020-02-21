package com.mars.mvvm.base.bean

import android.view.Gravity
import java.io.Serializable

/**
 * 通用Tab选项实体
 * @author Mars
 *
 */
class ComTabItemBean(text: String?, rid: Int, textColor: Int, drawableGravity: Int) : Serializable {
    /**
     *
     */
    var text: String? = null
    /**
     * 本地图片资源
     */
    var rid = 0

    /**
     * 图片显示位置
     */
    var drawableGravity = Gravity.TOP

    var textColor = 0x0000000

    init {
        this.text = text
        this.rid = rid
        this.drawableGravity = drawableGravity
        this.textColor = textColor
    }

    override fun toString(): String {
        return "ComTabItemBean(text=$text, rid=$rid, drawableGravity=$drawableGravity, textColor=$textColor)"
    }


}