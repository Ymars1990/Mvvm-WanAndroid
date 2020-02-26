package com.mars.mvvm.base.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.mars.mvvm.base.R
import com.mars.mvvm.base.databinding.FragmentBaseBinding

abstract class BaseFragment<Db : ViewDataBinding> : Fragment() {

    var db: Db? = null
    private var baseFragmentBinding: FragmentBaseBinding? = null
    protected var parentCtx: Context? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentCtx = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        baseFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false)
        baseFragmentBinding!!.contentFl.removeAllViews()
        db = DataBindingUtil.inflate(
            inflater,
            getLayoutResId(),
            baseFragmentBinding!!.contentFl,
            true
        )

        return baseFragmentBinding!!.root
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
    protected abstract fun getLayoutResId(): Int

    /**
     * 初始化数据
     */
    protected abstract fun initData()


    /**
     * @return
     */
    protected abstract fun doWork()
}