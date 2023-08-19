package com.example.teamproject10

import android.app.Activity
import android.app.Person
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.teamproject10.data.DetailData
import com.example.teamproject10.data.SignMember
import com.example.teamproject10.data.mainDataList
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val TAG: String = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDataAndEvent()

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

    private fun checkLogin() {
        // 로그인 여부 체크 후 문구 반영
        val loginCheck = findViewById<TextView>(R.id.login_check)

        if (SignMember.currentUser != null) {
            loginCheck.text = SignMember.currentUser?.name + getString(R.string.str_login)
        } else {
            loginCheck.text = getString(R.string.str_not_login)
        }

        val imgLoginmain = findViewById<LinearLayout>(R.id.login_layout)
        imgLoginmain.setOnClickListener  {
            //로그인 상태라면 마이페이지 or 로그인상태가 아니라면 로그인페이지
            if (SignMember.currentUser != null) {
                val i = Intent(this, MyPageActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    putExtra("DATA", getMydata())
                }
                startForMypage.launch(i)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            } else {
                val i = Intent(this, LoginActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                }
                startForLogin.launch(i)
            }
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
            val layoutHorizontalFeedIcon =
                inflater.inflate(R.layout.include_layout_feedicon, horizontalLayout, false)
            val icon1 = layoutHorizontalFeedIcon.findViewById<ImageView>(R.id.img_horizon1)
            icon1.setImageResource(detailData.profile.image)
            //그리고 마지막으로 아래 코드로 위에서 생성한 레아아웃을 붙여준다.
            horizontalLayout.addView(layoutHorizontalFeedIcon)

            layoutHorizontalFeedIcon.setOnClickListener {
                val i = Intent(this, DetailActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    putExtra("DATA", detailData)
                }
                startForDetail.launch(i)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }

            /* 세로 피드 구역 */
            detailData.detailList.forEach { feedData ->
                //반복해서 사용하는 layout_feeditem 레이아웃을 객체로 생성해서 변수에 할당한다.
                val layoutMainFeedItem =
                    inflater.inflate(
                        R.layout.include_layout_feeditem,
                        linearLayout,
                        false
                    ) as ViewGroup
                //위에서 가져온 레이아웃에 아래 위젯을 각각 찾아서 변수 할당 하고
                val imgIcon = layoutMainFeedItem.findViewById<ImageView>(R.id.img_icon_feed1)
                val tvId = layoutMainFeedItem.findViewById<TextView>(R.id.id_feed1)
                val imgFeed = layoutMainFeedItem.findViewById<ImageView>(R.id.img_feed1)
                val tvFeed = layoutMainFeedItem.findViewById<TextView>(R.id.feed1_textView)
                val tvMore = layoutMainFeedItem.findViewById<TextView>(R.id.feed_textmore)
                val idComment = layoutMainFeedItem.findViewById<TextView>(R.id.tv_comment_id)
                val comment = layoutMainFeedItem.findViewById<TextView>(R.id.tv_comment)

                //데이타를 넣어준다.
                imgIcon.setImageResource(detailData.profile.image)
                tvId.setText(detailData.profile.id)
                imgFeed.setImageResource(feedData.image)
                tvFeed.setText(feedData.feedDescription)
                idComment.setText(detailData.profile.id)
                comment.setText(feedData.comment)
                //더보기 함수 호출
                setViewMore(tvFeed, tvMore)
                // 그리고 마지막으로 아래 코드로 위에서 생성한 레아아웃을 붙여준다.
//                 linearLayout.addView(layoutMainFeedItem)
                // 랜덤한 위치에 세로 피드 구역 추가
                linearLayout.addRandomView(layoutMainFeedItem)

                layoutMainFeedItem.setOnClickListener {
                    val i = Intent(this, DetailActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        putExtra("DATA", detailData)
                    }
                    startForDetail.launch(i)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            }
        }
    }

    // 랜덤한 위치에 뷰를 추가하는 확장 함수
    private fun ViewGroup.addRandomView(child: ViewGroup) {
        if (childCount > 0) {
            val randomIndex = (0 until childCount).random()
            addView(child, randomIndex)
        } else {
            addView(child)
        }
    }

    // 더보기 기능 구현
    private fun setViewMore(contentTextView: TextView, viewMoreTextView: TextView) {
        // getEllipsisCount()을 통한 더보기 표시 및 구현
        contentTextView.post {
            val lineCount = contentTextView.layout.lineCount
            if (lineCount > 0) {
                if (contentTextView.layout.getEllipsisCount(lineCount - 1) > 0) {
                    // 더보기 표시
                    viewMoreTextView.visibility = View.VISIBLE

                    // 더보기 클릭 이벤트
                    viewMoreTextView.setOnClickListener {
                        contentTextView.maxLines = Int.MAX_VALUE
                        viewMoreTextView.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun getMydata(): DetailData? {
        val blackPink = mainDataList.subList(0, 4)
        val blackMemeber = blackPink.firstOrNull {
            getString(it.profile.id) == SignMember.currentUser?.id
        }
        return blackMemeber
    }

    /**
     * registerForActivityResult 로그인페이지 호출 후 setResult호출 시 콜백을 받는다
     */
    private val startForLogin =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                checkLogin()

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
        // 앞의 4명(고정 블핑맴버들)을 남기고 나머지 요소를 제거
        SignMember.signMemberList.subList(4, SignMember.signMemberList.size).clear()
        SignMember.currentUser = null
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}