package com.example.teamproject10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.example.teamproject10.data.DetailData

class DetailActivity : AppCompatActivity() {
    private val TAG: String = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //메인화면에서 선택하면 아래 코드로 전달받습니다.
        var detail: DetailData? = intent.getParcelableExtra("DATA")
        if(detail == null) {
            Toast.makeText(this, R.string.str_no_data, Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        //아래는 TestCode 데이타 활용
        Log.d(TAG, getString(detail.profile.nameRes))
        findViewById<ImageView>(R.id.testImg).setImageResource(detail.detailList[0].image)
        Log.d(TAG, detail.toString())
    }
}