package com.mars.mvvm.common_utils

/**
 * @author Mars
 * @desc Json格式化输出
 */
object JsonFormater {
    fun format(json: String?): String {
        val indent = StringBuffer()
        val sb = StringBuffer()
        for (c in json!!.toCharArray()) {
            when (c) {
                '{' -> {
                    indent.append(" ")
                    sb.append("{\n").append(indent)
                }
                '}' -> {
                    indent.deleteCharAt(indent.length - 1)
                    sb.append("\n").append(indent).append("}")
                }
                '[' -> {
                    indent.append(" ")
                    sb.append("[\n").append(indent)
                }
                ']' -> {
                    indent.deleteCharAt(indent.length - 1)
                    sb.append("\n").append(indent).append("]")
                }
                ',' -> sb.append(",\n").append(indent)
                else -> sb.append(c)
            }
        }
        return sb.toString()
    }
}