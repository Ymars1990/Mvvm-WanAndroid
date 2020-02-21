package com.mars.mvvm.common_utils.constant

import android.content.ComponentCallbacks2.*
import java.util.*

/**
 * @author Mars
 * 常量类
 */
class Constant {
    companion object {

        /**
         * 重复点击时长限制
         */
        const val DELAYMILLIS: Long = 500


        /**
         * ResultCode
         */
        const val MY_RESULT_OK = 0xA1
        const val PWD_SET_TYPE = "PWD_SET_TYPE"

        /**
         * 不同内存场景
         */
        val trimMemory: MutableMap<Int, String> = HashMap<Int, String>()

        init {
            trimMemory.put(
                TRIM_MEMORY_COMPLETE,
                "目前内存已经很低了，并且我们的程序处于LRU缓存列表的最边缘位置，系统会最优先考虑杀掉我们的应用程序，在这个时候应当尽可能地把一切可以释放的东西都进行释放"
            )
            trimMemory.put(
                TRIM_MEMORY_MODERATE,
                "目前内存已经很低了，并且我们的程序处于LRU缓存列表的中间位置，如果手机内存还得不到进一步释放的话，那么我们的程序就有被系统杀掉的风险了"
            )
            trimMemory.put(
                TRIM_MEMORY_BACKGROUND,
                "目前内存已经很低了，系统准备开始根据LRU缓存来清理进程。这个时候我们的程序在LRU缓存列表的最近位置，是不太可能被清理掉的，但这时去释放掉一些比较容易恢复的资源能够让手机的内存变得比较充足，从而让我们的程序更长时间地保留在缓存当中，这样当用户返回我们的程序时会感觉非常顺畅，而不是经历了一次重新启动的过程"
            )
            trimMemory.put(
                TRIM_MEMORY_UI_HIDDEN,
                "应用程序的所有UI界面被隐藏了，即用户点击了Home键或者Back键导致应用的UI界面不可见．这时候应该释放一些资源"
            )
            trimMemory.put(
                TRIM_MEMORY_RUNNING_CRITICAL,
                "应用程序仍然正常运行，但是系统已经根据LRU缓存规则杀掉了大部分缓存的进程了。这个时候我们应当尽可能地去释放任何不必要的资源，不然的话系统可能会继续杀掉所有缓存中的进程，并且开始杀掉一些本来应当保持运行的进程，比如说后台运行的服务"
            )
            trimMemory.put(
                TRIM_MEMORY_RUNNING_LOW,
                "应用程序正常运行，并且不会被杀掉。但是目前手机的内存已经非常低了，我们应该去释放掉一些不必要的资源以提升系统的性能，同时这也会直接影响到我们应用程序的性能"
            )
            trimMemory.put(
                TRIM_MEMORY_RUNNING_MODERATE,
                "应用程序正常运行，并且不会被杀掉。但是目前手机的内存已经有点低了，系统可能会开始根据LRU缓存规则来去杀死进程了"
            )
        }
    }

}