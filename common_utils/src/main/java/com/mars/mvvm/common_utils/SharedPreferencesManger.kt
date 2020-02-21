package com.mars.mvvm.common_utils

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

/**
 * @author Mars
 * SharedPreferences 管理类，可存取本地的SP数据
 */
object SharedPreferencesManger {
    var sp: SharedPreferences? = null
    var sp_eidtor: Editor? = null

    /**
     * 初始化 SharedPreferences,建议ctx 使用getApplicationContext(),初始化可在activity基类或者Application中
     *
     * @param ctx
     * @param sp_mode
     * @param sp_name
     */
    fun initSp(ctx: Context?, sp_mode: Int, sp_name: String) {
        sp = ctx!!.getSharedPreferences(sp_name!!, sp_mode)
        sp_eidtor = sp!!.edit()
    }

    /**
     * 存储键值对
     *
     * @param key   键
     * @param value 值
     */
    fun putValueInSp(key: String?, value: Any) {
        when {
            value is Int -> sp_eidtor!!.putInt(key, value)
            value is Long -> sp_eidtor!!.putLong(key, value)
            value is Float -> sp_eidtor!!.putFloat(key, value)
            value is String -> sp_eidtor!!.putString(key, value)
            value is Boolean -> sp_eidtor!!.putBoolean(key, value)
            value is Set<*> -> sp_eidtor!!.putStringSet(key, value as Set<String>)
        }
        sp_eidtor!!.commit()
    }

    /**
     * 获取整型数据
     *
     * @param key
     * @param defaultV
     * @return
     */
    fun getSpIntValue(key: String?, defaultV: Int): Int {
        return if (sp != null) {
            sp!!.getInt(key, defaultV)
        } else {
            defaultV
        }
    }

    /**
     * 获取长整型数据
     *
     * @param key
     * @param defaultV
     * @return
     */
    fun getSpLongValue(key: String?, defaultV: Long): Long {
        return if (sp != null) {
            sp!!.getLong(key, defaultV)
        } else {
            defaultV
        }
    }

    /**
     * 获取浮点型数据
     *
     * @param key
     * @param defaultV
     * @return
     */
    fun getSpFloatValue(key: String?, defaultV: Float): Float {
        return if (sp != null) {
            sp!!.getFloat(key, defaultV)
        } else {
            defaultV
        }
    }

    /**
     * 获取布尔型数据
     *
     * @param key
     * @param defaultV
     * @return
     */
    fun getSpBooleanValue(key: String?, defaultV: Boolean): Boolean {
        return if (sp != null) {
            sp!!.getBoolean(key, defaultV)
        } else {
            defaultV
        }
    }

    /**
     * 获取字符串型数据
     *
     * @param key
     * @param defaultV
     * @return
     */
    fun getSpStringValue(key: String?, defaultV: String?): String? {
        return if (sp != null) {
            sp!!.getString(key, defaultV)
        } else {
            defaultV
        }
    }


    /**
     * 获取Set集合字符串型数据
     *
     * @param key
     * @param defaultV
     * @return
     */
    fun getSpSetStringValue(key: String?, defaultV: Set<String?>?): Set<String?>? {
        return if (sp != null) {
            sp!!.getStringSet(key, defaultV)
        } else {
            defaultV
        }
    }

    /**
     * 清空Sp 所有数据
     */
    fun clearSp() {
        sp_eidtor!!.clear()
        sp_eidtor!!.commit()
    }

    /**
     * 删除指定数据
     *
     * @param key 键
     */
    fun removeSpKey(key: String?) {
        sp_eidtor!!.remove(key)
        sp_eidtor!!.commit()
    }
}
