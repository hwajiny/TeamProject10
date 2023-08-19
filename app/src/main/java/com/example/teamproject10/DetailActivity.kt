package com.example.teamproject10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.teamproject10.data.DetailData

class DetailActivity : AppCompatActivity() {
    private val TAG: String = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var detail: DetailData? = intent.getParcelableExtra("DATA")
        if (detail == null) {
            Toast.makeText(this, R.string.str_no_data, Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        val profileImage = findViewById<ImageView>(R.id.img_jn1)
        profileImage.setImageResource(detail.profile.image)

        val profileName = findViewById<TextView>(R.id.textView1)
        profileName.setText(detail.profile.nameRes)

        val linearLayout = findViewById<LinearLayout>(R.id.layout_content)

        detail.detailList.forEach { feedData ->
            val inflater = LayoutInflater.from(this)
            val layoutDetailFeedItem = inflater.inflate(R.layout.include_layout_feeditem, linearLayout, false) as ViewGroup

            val imgIcon = layoutDetailFeedItem.findViewById<ImageView>(R.id.img_icon_feed1)
            val tvIcon = layoutDetailFeedItem.findViewById<TextView>(R.id.id_feed1)
            val imgFeed = layoutDetailFeedItem.findViewById<ImageView>(R.id.img_feed1)
            val tvFeed = layoutDetailFeedItem.findViewById<TextView>(R.id.feed1_textView)
            val idComment = layoutDetailFeedItem.findViewById<TextView>(R.id.tv_comment_id)
            val comment = layoutDetailFeedItem.findViewById<TextView>(R.id.tv_comment)

            imgIcon.setImageResource(detail.profile.image)
            tvIcon.setText(detail.profile.id)
            imgFeed.setImageResource(feedData.image)
            tvFeed.setText(feedData.feedDescription)
            idComment.setText(detail.profile.id)
            comment.setText(feedData.comment)

            linearLayout.addView(layoutDetailFeedItem)
        }
    }
}
