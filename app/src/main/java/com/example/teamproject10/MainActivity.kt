package com.example.teamproject10

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.teamproject10.data.UserData
import com.example.teamproject10.data.mainDataList
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val TAG: String = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDataAndEvent()

        val imgLoginmain = findViewById<ImageView>(R.id.img_login_main)
        //일단은 우측상단 아이콘을 누르면 마이페이지로 이동하도록 반영. 추후 수정됨
        imgLoginmain.setOnClickListener {
            val i = Intent(this, MyPageActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            startForMypage.launch(i)
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
        }

        checkLogin()

        //플로팅 버튼을 누르면 플로팅 화면을 호출하는 코드
        val btnFloating = findViewById<FloatingActionButton>(R.id.btn_floating)
        btnFloating.setOnClickListener {
            val i = Intent(this, FloatingActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            startForFloating.launch(i)
            Log.d(TAG, "startForFloating")
        }
    }

    /**
     * 앱이 구동되면서 데이타를 설정하고 이벤트를 추가한다
     */
    private fun initDataAndEvent() {
        //layout_content 레이아웃을 가져와서 linearLayout 변수를 하나 선언해서 넣어준다.
        val horizontalLayout = findViewById<LinearLayout>(R.id.horizontal_layout)
        //layout_content 레이아웃을 가져와서 linearLayout 변수를 하나 선언해서 넣어준다.
        val linearLayout = findViewById<LinearLayout>(R.id.layout_content)
        //XML레이아웃코드를 직접(코드) 사용하기 위해 객체화하는 과정
        val inflater = LayoutInflater.from(this)

        //아래 forEach문을통해서 피드데이타 갯수만큼 반복하면서 addView를 통해서 뷰를 붙여준다.
        mainDataList.forEach { detailData ->
            /* 가로 프로필 이미지 구역 */
            //반복해서 사용하는 layout_feeditem 레이아웃을 객체로 생성해서 변수에 할당한다.
            val layoutHorizontalFeedIcon =
                inflater.inflate(R.layout.include_layout_feedicon, horizontalLayout, false)
            val icon1 = layoutHorizontalFeedIcon.findViewById<ImageView>(R.id.img_horizon1)
            icon1.setImageResource(detailData.profile.image)
            //그리고 마지막으로 아래 코드로 위에서 생성한 레아아웃을 붙여준다.
            horizontalLayout.addView(layoutHorizontalFeedIcon)

            layoutHorizontalFeedIcon.setOnClickListener {
                val i = Intent(this, DetailActivityJE::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    putExtra("DATA", detailData)
                }
                startForDetail.launch(i)
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
            }

            /* 세로 피드 구역 */
            //반복해서 사용하는 layout_feeditem 레이아웃을 객체로 생성해서 변수에 할당한다.
            val layoutMainFeedItem =
                inflater.inflate(R.layout.include_layout_feeditem, linearLayout, false) as ViewGroup
            //위에서 가져온 레이아웃에 아래 위젯을 각각 찾아서 변수 할당 하고
            val imgIcon = layoutMainFeedItem.findViewById<ImageView>(R.id.img_icon_feed1)
            val tvIcon = layoutMainFeedItem.findViewById<TextView>(R.id.id_feed1)
            val imgFeed = layoutMainFeedItem.findViewById<ImageView>(R.id.img_feed1)
            val tvFeed = layoutMainFeedItem.findViewById<TextView>(R.id.feed1_textView)
            val idComment = layoutMainFeedItem.findViewById<TextView>(R.id.tv_comment_id)
            val comment = layoutMainFeedItem.findViewById<TextView>(R.id.tv_comment)

            //데이타를 넣어준다.
            imgIcon.setImageResource(detailData.profile.image)
            tvIcon.setText(detailData.profile.id)
            imgFeed.setImageResource(detailData.detailList[0].image)
            tvFeed.setText(detailData.detailList[0].feedDescription)
            idComment.setText(detailData.profile.id)
            comment.setText(detailData.detailList[0].comment)

            //그리고 마지막으로 아래 코드로 위에서 생성한 레아아웃을 붙여준다.
            linearLayout.addView(layoutMainFeedItem)

            layoutMainFeedItem.setOnClickListener {
                val i = Intent(this, DetailActivityJE::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    putExtra("DATA", detailData)
                }
                startForDetail.launch(i)
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
            }
        }
    }

    private fun checkLogin() {
        // 로그인 여부 체크 후 문구 반영
        val loginCheck = findViewById<TextView>(R.id.login_check)
        if (UserData.id.isNotEmpty()) {
            loginCheck.text = getString(R.string.str_login)
        } else {
            loginCheck.text = getString(R.string.str_not_login)
        }
        loginCheck.setOnClickListener {
            //로그인 상태라면 마이페이지 or 로그인상태가 아니라면 로그인페이지
            if (UserData.id.isNotEmpty()) {
                val i = Intent(this, MyPageActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                }
                startForMypage.launch(i)

            } else {
                val i = Intent(this, LoginActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                }
                startForLogin.launch(i)
            }

        }
    }

    /**
     * registerForActivityResult 로그인페이지 호출 후 setResult호출 시 콜백을 받는다
     */
    private val startForLogin =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                checkLogin()

                Log.d(TAG, "startForLoginResult")
            }
        }

    /**
     * registerForActivityResult 마이페이지 호출 후 setResult호출 시 콜백을 받는다
     */
    private val startForMypage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
            }
        }

    /**
     * registerForActivityResult 상세페이지 호출 후 setResult호출 시 콜백을 받는다
     */
    private val startForDetail =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
            }
        }

    /**
     * registerForActivityResult 플로팅 페이지로 이동 후 setResult호출 시 콜백을 받는다
     */
    private val startForFloating =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        UserData.id = ""
        UserData.pw = ""
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}