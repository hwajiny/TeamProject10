package com.example.teamproject10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.teamproject10.R

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)
    }
    fun showToast(view: View) {
        Toast.makeText(this, "지금은 프로필 수정하실 수 없습니다!", Toast.LENGTH_SHORT).show()
    }
}