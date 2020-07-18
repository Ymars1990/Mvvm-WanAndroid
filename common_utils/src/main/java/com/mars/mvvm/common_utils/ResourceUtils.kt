package com.mars.mvvm.common_utils

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources

class ResourceUtils {

    companion object {
        @JvmStatic
        fun getColor(mCtx: Context, color: Int): ColorStateList {
            return AppCompatResources.getColorStateList(
                mCtx!!, color
            )
        }

        @JvmStatic
        fun getDrawable(mCtx: Context, drawableId:  Int): Drawable ?{
            return AppCompatResources.getDrawable(mCtx!!,drawableId)
        }
    }
}