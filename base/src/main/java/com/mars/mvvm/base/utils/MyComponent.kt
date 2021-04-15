package com.mars.mvvm.base.utils

import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingComponent

class MyComponent : DataBindingComponent {
    override fun getMyComponent(): MyComponent {
        return MyComponent()
    }

    @BindingAdapter("app:icon_src_normal", requireAll = false)
    fun getDrawable(imageView: AppCompatTextView, drawableId: Int): Drawable {
        return AppCompatResources.getDrawable(imageView!!.context, drawableId)!!
    }

}