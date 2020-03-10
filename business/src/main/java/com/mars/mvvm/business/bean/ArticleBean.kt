package com.mars.mvvm.business.bean

import android.util.SparseArray

/**
 * @author Mars
 * 文章实体
 *  * @exp: {
 * "apkLink":"",
 * "audit":1,
 * "author":"",
 * "chapterId":67,
 * "chapterName":"网络基础",
 * "collect":false,
 * "courseId":13,
 * "desc":"",
 * "envelopePic":"",
 * "fresh":false,
 * "id":9704,
 * "link":"https://juejin.im/post/5d9d539ee51d45780f0604fa",
 * "niceDate":"2天前",
 * "niceShareDate":"2天前",
 * "origin":"",
 * "prefix":"",
 * "projectLink":"",
 * "publishTime":1571154375000,
 * "selfVisible":0,
 * "shareDate":1571153486000,
 * "shareUser":"鸿洋",
 * "superChapterId":98,
 * "superChapterName":"网络访问",
 * "tags":[
 * ],
 * "title":"看完这篇还不清楚缓存，求你打我（有彩蛋）",
 * "type":0,
 * "userId":2,
 * "visible":1,
 * "zan":0
 * }
 */
class ArticleBean(
    var apkLink: String,
    var isTop: Boolean,
    var audit: Int,
    var author: String,
    var chapterId: Long,
    var chapterName: String,
    var collect: Boolean,
    var courseId: Long,
    var desc: String,
    var envelopePic: String,
    var fresh: Boolean,
    var id: Long,
    var link: String,
    var niceDate: String,
    var niceShareDate: String,
    var origin: String,
    var prefix: String,
    var projectLink: String,
    var publishTime: Long,
    var selfVisible: Int,
    var shareDate: Long,
    var shareUser: String,
    var superChapterId: Long,
    var superChapterName: String,
    var tags: ArrayList<TagsBean>,
    var title: String,
    var type: Int,
    var userId: Long,
    var zan: Int,
    var visible: Int
) {
}