package com.mars.mvvm.common_utils

import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import com.mars.mvvm.common_utils.LogManger.Companion.logE
import com.mars.mvvm.common_utils.LogManger.Companion.logI


object ViewMutiUtils {
    /**
     * 禁止Editexr 内容复制粘贴
     *
     * @param editText
     */
    fun forbidenEditextPaste(editText: EditText?) {
        if (editText != null) {
            editText.isLongClickable = false
            editText.setTextIsSelectable(false)
            editText.customSelectionActionModeCallback = object :
                ActionMode.Callback {
                override fun onCreateActionMode(
                    actionMode: ActionMode,
                    menu: Menu
                ): Boolean {
                    logI("forbidenEditextPaste", "onCreateActionMode")
                    return false
                }

                override fun onPrepareActionMode(
                    actionMode: ActionMode,
                    menu: Menu
                ): Boolean {
                    logI("forbidenEditextPaste", "onPrepareActionMode")
                    return false
                }

                override fun onActionItemClicked(
                    actionMode: ActionMode,
                    menuItem: MenuItem
                ): Boolean {
                    logI("forbidenEditextPaste", "onActionItemClicked")
                    return false
                }

                override fun onDestroyActionMode(actionMode: ActionMode) {
                    logI("forbidenEditextPaste", "onDestroyActionMode")
                }
            }
        }
    }

    /**
     * 设置控件背景
     *
     * @param drawable
     * @param view
     */
    fun setBackground(drawable: GradientDrawable?, view: View?) {
        /**
         * 判断当前版本号，版本号大于等于16，使用setBackground；版本号小于16，使用setBackgroundDrawable。
         */
        if (drawable == null || view == null) {
            logE("Exception", "drawable or view is null")
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.background = drawable
        } else {
            view.setBackgroundDrawable(drawable)
        }
    }
}