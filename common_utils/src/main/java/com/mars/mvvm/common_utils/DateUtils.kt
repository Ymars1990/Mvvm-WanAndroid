package com.mars.mvvm.common_utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author mars
 * 日期转换工具
 */
object DateUtils {
    val formter01 = "yyyy-MM-dd HH:mm:ss"
    /**
     * long 转格式 2010-10-10 15:00:00
     */
    @Throws(ParseException::class)
    fun longToString(currentTime: Long, formatType: String?): String {
        val date: Date = longToDate(currentTime, formatType)
        return dateToString(date, formatType)
    }

    /**
     * currentTime要转换的long类型的时间
     * formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
     */
    @Throws(ParseException::class)
    fun longToDate(currentTime: Long, formatType: String?): Date {
        val dateOld = Date(currentTime)
        val sDateTime: String = dateToString(dateOld, formatType)
        return stringToDate(sDateTime, formatType)
    }

    /**
     * strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
     * HH时mm分ss秒，
     * strTime的时间格式必须要与formatType的时间格式相同
     */
    @Throws(ParseException::class)
    fun stringToDate(strTime: String?, formatType: String?): Date {
        val formatter = SimpleDateFormat(formatType)
        var date: Date? = null
        date = formatter.parse(strTime)
        return date
    }

    /**
     * formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
     * data Date类型的时间
     */
    fun dateToString(data: Date?, formatType: String?): String {
        return SimpleDateFormat(formatType).format(data)
    }
}