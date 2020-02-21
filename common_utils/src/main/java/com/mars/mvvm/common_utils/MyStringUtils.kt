package com.mars.mvvm.common_utils

/**
 * @author Mars
 */
object MyStringUtils {
    /**
     * 判断字符串 不等于null 和"" 和全为空格
     *
     * @param src
     * @return
     */
    fun stringIsNotNull(src: String?): Boolean {
        return src != null && "" != src && "" != src.trim { it <= ' ' } && src.trim { it <= ' ' }.toLowerCase() != "null"
    }

    /**
     * 类型转字符串
     * \* @param str
     *
     * @return
     */
    fun transformToString(str: Any?): String {
        return if (str != null) {
            if (str is Int || str is Double || str is Float || str is Short
                || str is Byte || str is Char || str is Long
            ) {
                str.toString()
            } else if (str is String) {
                str
            } else {
                str.toString()
            }
        } else {
            "str is null"
        }
    }
}
