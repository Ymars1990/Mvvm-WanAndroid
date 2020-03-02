package com.mars.mvvm.base.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

import androidx.fragment.app.Fragment
import com.mars.mvvm.base.R
import com.mars.mvvm.base.viewmodel.BaseViewModel

abstract class BaseFragment : Fragment() {

    protected var parentCtx: Context? = null

    var rootView: View? = null
    var contentFl: FrameLayout? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentCtx = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_base, container, false)
        contentFl = rootView!!.findViewById(R.id.contentFl)
        contentFl!!.removeAllViews()
        val sonView: View =
            LayoutInflater.from(parentCtx)
                .inflate(getLayoutResId(savedInstanceState), contentFl, false)
        contentFl!!.addView(sonView)
        initView()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initData()
        doWork()
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * 获取当前页面的布局资源ID
     *
     * @return 布局资源ID
     */
    protected abstract fun getLayoutResId(savedInstanceState: Bundle?): Int

    /**
     *初始化控件
     */
    protected abstract fun initView()

    /**
     * 初始化数据
     */
    protected abstract fun initData()

    /**
     * @return
     */
    protected abstract fun doWork()
}