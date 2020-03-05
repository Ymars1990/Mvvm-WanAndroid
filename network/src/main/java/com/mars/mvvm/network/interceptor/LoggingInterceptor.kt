package com.mars.mvvm.network.interceptor

import com.mars.mvvm.common_utils.JsonFormater
import com.mars.mvvm.common_utils.LogManger
import okhttp3.*
import okhttp3.internal.http.HttpHeaders
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.UnsupportedCharsetException
import java.util.concurrent.TimeUnit


/**
 * @author Mars
 * 日志拦截器
 */
class LoggingInterceptor : Interceptor {
    private val UTF8: Charset = Charset.forName("UTF-8")
    private val TAG = this.javaClass.simpleName
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val request: Request = chain.request()
        val requestBody: RequestBody = request.body()!!
        var body: String? = null
        if (requestBody != null) {
            val buffer = Buffer()
            requestBody.writeTo(buffer)
            var charset: Charset = UTF8
            val contentType: MediaType? = requestBody.contentType()
            if (contentType != null) {
                charset = contentType.charset(UTF8)!!
            }
            body = buffer.readString(charset)
        }
        LogManger.logE(
            TAG,
            String.format(
                "发送请求\nmethod：%s\nurl:%s\nheaders:%sbody:%s",
                request.method(),
                request.url(),
                request.headers(),
                body
            )
        )
        val startNs = System.nanoTime()
        val response = chain.proceed(request)
        val tookMs: Long =
            TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
        val responseBody = response.body()
        var rBody: String? = null
        if (HttpHeaders.hasBody(response)) {
            val source = responseBody!!.source()
            source.request(Long.MAX_VALUE) // Buffer the entire body.
            val buffer: Buffer = source.buffer()
            var charset: Charset = UTF8
            val contentType: MediaType? = responseBody.contentType()
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8)!!
                } catch (e: UnsupportedCharsetException) {
                    e.printStackTrace()
                }
            }
            rBody = buffer.clone().readString(charset)
        }
        LogManger.logE(
            TAG,
            String.format(
                "收到响应 %s%s %ss\n请求url:%s\n请求body:%s\n请求header:%s响应body:",
                response.code(),
                response.message(),
                tookMs,
                response.request().url(),
                JsonFormater.format(rBody),
                request.headers()
            )
        )
//        LogManger.logE(TAG, JsonFormater.format(rBody))
        return response
    }
}