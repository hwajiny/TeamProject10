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
import com.example.teamproject10.data.mainDataList
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val TAG: String = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDataAndEvent()

        val imgLoginmain = findViewById<ImageView>(R.id.img_login_main)
        imgLoginmain.setOnClickListener {
            val i = Intent(this, MyPageActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            startForMypage.launch(i)

        }

//        val itemFeed1 = findViewById<LinearLayout>(R.id.layout_item_feed1)
//        itemFeed1.setOnClickListener {
//            val i = Intent(this, DetailActivity::class.java).apply {
//                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
//                putExtra("DATA", mockDataList[0])
//            }
//            startForDetail.launch(i)
//
//        }
//
//        val itemFeed2 = findViewById<LinearLayout>(R.id.layout_item_feed2)
//        itemFeed2.setOnClickListener {
//            val i = Intent(this, DetailActivity::class.java).apply {
//                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
//                putExtra("DATA", mockDataList[1])
//            }
//            startForDetail.launch(i)
//        }
//
//        val itemFeed3 = findViewById<LinearLayout>(R.id.layout_item_feed3)
//        itemFeed3.setOnClickListener {
//            val i = Intent(this, DetailActivity::class.java).apply {
//                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
//                putExtra("DATA", mockDataList[2])
//            }
//            startForDetail.launch(i)
//        }
//
//        val itemFeed4 = findViewById<LinearLayout>(R.id.layout_item_feed4)
//        itemFeed4.setOnClickListener {
//            val i = Intent(this, DetailActivity::class.java).apply {
//                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
//                putExtra("DATA", mockDataList[3])
//            }
//            startForDetail.launch(i)
//        }

        val btnFloating = findViewById<FloatingActionButton>(R.id.btn_floating)
        btnFloating.setOnClickListener {
            val i = Intent(this, FloatingActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            startForFloating.launch(i)
            Log.d(TAG, "startForFloating")
        }

    }

    private fun initDataAndEvent() {
        val linearLayout = findViewById<LinearLayout>(R.id.layout_content)
        val inflater = LayoutInflater.from(this)

        mainDataList.forEachIndexed { index, detailData ->
            val layoutMainFeedItem = inflater.inflate(R.layout.include_layout_feeditem, linearLayout, false) as ViewGroup
            //데이타 추가
            val imgIcon = layoutMainFeedItem.findViewById<ImageView>(R.id.img_icon_feed1)
            val tvIcon = layoutMainFeedItem.findViewById<TextView>(R.id.id_feed1)
            val imgFeed = layoutMainFeedItem.findViewById<ImageView>(R.id.img_feed1)
            val tvFeed = layoutMainFeedItem.findViewById<TextView>(R.id.feed1_textView)
            val idComment = layoutMainFeedItem.findViewById<TextView>(R.id.tv_comment_id)
            val comment = layoutMainFeedItem.findViewById<TextView>(R.id.tv_comment)

            when(index) {
                0 ->{
                    imgIcon.setImageResource(R.drawable.ic_jisu)
                    tvIcon.setText(R.string.str_jisu_id)
                    imgFeed.setImageResource(R.drawable.jisufeed)
                    tvFeed.setText(R.string.str_jisu_feed)
                    idComment.setText(R.string.str_jisu_id)
                    comment.setText(R.string.str_jisu_comment)
                }
                1 ->{
                    imgIcon.setImageResource(R.drawable.ic_jenny)
                    tvIcon.setText(R.string.str_jenny_id)
                    imgFeed.setImageResource(R.drawable.jennyfeed)
                    tvFeed.setText(R.string.str_jenny_feed)
                    idComment.setText(R.string.str_jenny_id)
                    comment.setText(R.string.str_jenny_comment)
                }
                2 ->{
                    imgIcon.setImageResource(R.drawable.ic_rose)
                    tvIcon.setText(R.string.str_rose_id)
                    imgFeed.setImageResource(R.drawable.rosefeed)
                    tvFeed.setText(R.string.str_rose_feed)
                    idComment.setText(R.string.str_rose_id)
                    comment.setText(R.string.str_rose_comment)
                }
                3 ->{
                    imgIcon.setImageResource(R.drawable.ic_lisa)
                    tvIcon.setText(R.string.str_lisa_id)
                    imgFeed.setImageResource(R.drawable.lisafeed)
                    tvFeed.setText(R.string.str_lisa_feed)
                    idComment.setText(R.string.str_lisa_id)
                    comment.setText(R.string.str_lisa_comment)
                }
                else -> {}
            }

            //스크롤뷰에 뷰추가
            linearLayout.addView(layoutMainFeedItem)

            layoutMainFeedItem.setOnClickListener {
                val i = Intent(this, DetailActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    putExtra("DATA", detailData)
                }
                startForDetail.launch(i)
            }
        }
    }

    private val startForMypage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                Log.d(TAG, "startForLoginResult")
                // 로그인 성공 시, 이름과 이미지 전달 받음
            }
        }

    private val startForDetail =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                Log.d(TAG, "startForDetailResult")
                // 특별히 콜백이 필요해 보진 않음
            }
        }

    private val startForFloating =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                Log.d(TAG, "startForFloatingResult")
                // 특별히 콜백이 필요해 보진 않음
            }
        }

}