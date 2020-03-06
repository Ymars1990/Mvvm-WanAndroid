package com.mars.mvvm.network

import com.google.gson.GsonBuilder
import com.mars.mvvm.common_utils.constant.Constant
import com.mars.mvvm.network.gsonfactory.*
import com.mars.mvvm.network.interceptor.LoggingInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Mars
 * 请求管理工厂
 * 负责发送和接收数据
 */
class RetrofitManagerFactory private constructor() {

    private val retrofit: Retrofit

    fun <T> create(clz: Class<T>): T {
        return retrofit.create(clz)
    }

    companion object {
        val instance by lazy {
            RetrofitManagerFactory()
        }
    }

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(BaseConverterFactory.create())
            .addConverterFactory(NobodyConverterFactory.create())
            .addConverterFactory(initGsonConverterFactory())
            .client(initOkHttpClient())
            .build();
    }


    private fun initOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(LoggingInterceptor())
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(initCommonInterceptor())
            .followRedirects(false)
            .followSslRedirects(true)
//            .cookieJar(new CookieManger(mCtx))
            .build()
    }

    private fun initCommonInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("charset", "UTF-8")
                .build()

            chain.proceed(request)
        }
    }

    private fun initGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(
            GsonBuilder()
                .registerTypeAdapter(Int::class.java, IntegerDefault0Adapter())
                .registerTypeAdapter(Int::class.javaPrimitiveType, IntegerDefault0Adapter())
                .registerTypeAdapter(Double::class.java, DoubleDefault0Adapter())
                .registerTypeAdapter(
                    Double::class.javaPrimitiveType,
                    DoubleDefault0Adapter()
                )
                .registerTypeAdapter(Long::class.java, LongDefault0Adapter())
                .registerTypeAdapter(
                    Long::class.javaPrimitiveType,
                    LongDefault0Adapter()
                )
                .registerTypeAdapter(String::class.java, StringDefault0Adapter())
                .create()
        )

    }
}



