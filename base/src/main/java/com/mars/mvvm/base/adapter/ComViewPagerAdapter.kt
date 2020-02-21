package com.mars.mvvm.base.adapter

import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

/**
 * @author Mars
 * Fragment 通用适配器
 */
class ComViewPagerAdapter<T : Fragment?> : FragmentPagerAdapter {
    private var fragments: SparseArray<T> = SparseArray()

    constructor(fm: FragmentManager?) : super(fm!!) {}
    constructor(fm: FragmentManager?, behavior: Int) : super(fm!!, behavior) {}
    constructor(fm: FragmentManager?, fragments: SparseArray<T>) : this(
        fm
    ) {
        this.fragments = fragments
    }

    constructor(fm: FragmentManager?, behavior: Int, fragments: SparseArray<T>) : this(
        fm,
        behavior
    ) {
        this.fragments = fragments
    }

    override fun getItem(i: Int): Fragment {
        return fragments!![i]!!
    }

    override fun getCount(): Int {
        return fragments!!.size()
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) { //        super.destroyItem(container, position, object);
    }
}