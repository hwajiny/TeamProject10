package com.example.teamproject10.data

/**
 * 싱글턴으로 회원정보 관리
 */
object SignMember {
    var signMemberList = mutableListOf<UserData>()
    init {
        signMemberList.add(UserData("지수","jisu","111"))
        signMemberList.add(UserData("제니","jenny","111"))
        signMemberList.add(UserData("로제","rose","111"))
        signMemberList.add(UserData("리사","lisa","111"))
    }

    //로그인 상태의 유저
    var currentUser: UserData? = null
}
data class UserData(val name:  String, val id: String, val pw: String)