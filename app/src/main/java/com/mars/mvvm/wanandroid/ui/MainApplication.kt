package com.mars.mvvm.wanandroid.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.mars.mvvm.base.ui.BaseActivity
import com.mars.mvvm.common_utils.LogManger
import com.mars.mvvm.common_utils.constant.Constant
import com.mars.mvvm.wanandroid.R
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.tencent.smtt.sdk.QbSdk
import com.tencent.smtt.sdk.QbSdk.PreInitCallback
import java.lang.String
import java.util.*


class MainApplication : MultiDexApplication() {
    protected var store: Stack<BaseActivity>? = null

    protected var count = 0

    companion object {
        init {
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator(DefaultRefreshHeaderCreator { context, layout ->
                ClassicsHeader(context).setPrimaryColorId(R.color.blackeeeeee)
            })
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator(DefaultRefreshFooterCreator() { context, layout ->
                ClassicsFooter(context).setPrimaryColorId(R.color.blackeeeeee).setDrawableSize(20f)
            })
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        store = Stack()
        initLifecycleCallback()
        ARouter.openDebug()
        ARouter.openLog()
        ARouter.init(this)

        initX5()
    }

    private fun initX5() {
        //非wifi情况下，主动下载x5内核

        //非wifi情况下，主动下载x5内核
        QbSdk.setDownloadWithoutWifi(true)
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        val cb: PreInitCallback = object : PreInitCallback {
            override fun onViewInitFinished(arg0: Boolean) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
            }

            override fun onCoreInitFinished() {}
        }
        //x5内核初始化接口
        //x5内核初始化接口
        QbSdk.initX5Environment(applicationContext, cb)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        /**
         * 后台应用程序终止，但前台用用程序内存还不够时调用该方法，可在该方法中释放一些不必要的资源来应对这种情况
         */
        LogManger.logE("BaseApplication", "[onLowMemory()]")
    }

    override fun onTerminate() {
        super.onTerminate()
        /**
         * 终止应用程序时调用，不能保证一定会被调用
         */
        LogManger.logE("BaseApplication", "[onTerminate()]")
        ARouter.getInstance().destroy()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        /**
         * 通知应用的不同内存情况
         */
        LogManger.logE(
            "BaseApplication",
            String.format("[onTrimMemory():%s]", Constant.trimMemory.get(level))
        )
    }

    private fun initLifecycleCallback() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(
                activity: Activity,
                savedInstanceState: Bundle?
            ) {
                LogManger.logE(
                    "Lifecycle",
                    activity.localClassName + " was Created"
                )
                if (activity is BaseActivity) {
                    store!!.add(activity)
                }
            }

            override fun onActivityStarted(activity: Activity) {
                LogManger.logE(
                    "Lifecycle",
                    activity.localClassName + " was Started"
                )
                count++
                if (count == 1) { //后台切换到前台
                    LogManger.logE("Lifecycle", ">>>>>>>>>>>>>>>>>>>App切到前台")
                }
            }

            override fun onActivityResumed(activity: Activity) {
                LogManger.logE(
                    "Lifecycle",
                    activity.localClassName + " was oResumed"
                )
            }

            override fun onActivityPaused(activity: Activity) {
                LogManger.logE(
                    "Lifecycle",
                    activity.localClassName + " was Pauseed"
                )
            }

            override fun onActivityStopped(activity: Activity) {
                LogManger.logE(
                    "Lifecycle",
                    activity.localClassName + " was Stoped"
                )
                count--
                //数值从1到0说明是从前台切到后台
                if (count == 0) {
                    //从前台切到后台
                    LogManger.logE("Lifecycle", ">>>>>>>>>>>>>>>>>>>App切到后台")
                }
            }

            override fun onActivitySaveInstanceState(
                activity: Activity,
                outState: Bundle
            ) {
                LogManger.logE(
                    "Lifecycle",
                    activity.localClassName + " was SaveInstanceState"
                )
            }

            override fun onActivityDestroyed(activity: Activity) {
                LogManger.logE(
                    "Lifecycle", activity.localClassName + " was Destroyed"
                )
                if (store != null) {
                    store!!.remove(activity)
                }
            }
        })
    }

    // 获取当前的Activity
    fun getCurActivity(): Activity? {
        return store!!.lastElement()
    }
}