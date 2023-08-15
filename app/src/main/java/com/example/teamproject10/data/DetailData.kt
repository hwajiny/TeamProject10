package com.example.teamproject10.data

import android.os.Parcelable
import com.example.teamproject10.R
import kotlinx.parcelize.Parcelize

/**
 * 상세화면 데이타
 */
@Parcelize
data class DetailData(var profile: ProfileData, var detailList: ArrayList<FeedData>): Parcelable

/**
 * 상세 프로필
 */
@Parcelize
data class ProfileData(var id: Int, var nameRes: Int, var descriptionRes: Int, var mbtiRes: Int, var image: Int): Parcelable

/**
 * 상세 데이타
 */
@Parcelize
data class FeedData(var image: Int, var feedDescription: Int, var comment: Int): Parcelable


/**
 * 아래는 데이타 입니다.
 */

val jisuProfile = ProfileData(R.string.str_jisu_id, R.string.str_jisu_name, R.string.str_jisu_des, R.string.str_jisu_mbti, R.drawable.ic_jisu)
val jisuFeed = ArrayList<FeedData>().apply {
    add(FeedData(R.drawable.jisufeed, R.string.str_jisu_feed, R.string.str_jisu_comment))
    add(FeedData(R.drawable.jisufeed, R.string.str_jisu_feed, R.string.str_jisu_comment))
    add(FeedData(R.drawable.jisufeed, R.string.str_jisu_feed, R.string.str_jisu_comment))
}

val jennyProfile = ProfileData(R.string.str_jenny_id, R.string.str_jenny_name, R.string.str_jenny_des, R.string.str_jenny_mbti, R.drawable.ic_jenny)
val jennyFeed = ArrayList<FeedData>().apply {
    add(FeedData(R.drawable.jennyfeed, R.string.str_jenny_feed, R.string.str_jenny_comment))
    add(FeedData(R.drawable.jennyfeed, R.string.str_jenny_feed, R.string.str_jenny_comment))
    add(FeedData(R.drawable.jennyfeed, R.string.str_jenny_feed, R.string.str_jenny_comment))
}

val roseProfile = ProfileData(R.string.str_rose_id, R.string.str_rose_name, R.string.str_rose_des, R.string.str_rose_mbti, R.drawable.ic_rose)
val roseFeed = ArrayList<FeedData>().apply {
    add(FeedData(R.drawable.rosefeed, R.string.str_rose_feed, R.string.str_rose_comment))
    add(FeedData(R.drawable.rosefeed, R.string.str_rose_feed, R.string.str_rose_comment))
    add(FeedData(R.drawable.rosefeed, R.string.str_rose_feed, R.string.str_rose_comment))
}

val lisaProfile = ProfileData(R.string.str_lisa_id, R.string.str_lisa_name, R.string.str_lisa_des, R.string.str_lisa_des, R.drawable.ic_lisa)
val lisaFeed = ArrayList<FeedData>().apply {
    add(FeedData(R.drawable.lisafeed, R.string.str_lisa_feed, R.string.str_lisa_comment))
    add(FeedData(R.drawable.lisafeed, R.string.str_lisa_feed, R.string.str_lisa_comment))
    add(FeedData(R.drawable.lisafeed, R.string.str_lisa_feed, R.string.str_lisa_comment))
}

var mainDataList = ArrayList<DetailData>().apply {
    add(DetailData(jisuProfile, jisuFeed))
    add(DetailData(jennyProfile, jennyFeed))
    add(DetailData(roseProfile, roseFeed))
    add(DetailData(lisaProfile, lisaFeed))
}