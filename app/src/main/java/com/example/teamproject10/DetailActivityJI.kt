package com.example.teamproject10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.teamproject10.data.DetailData

class DetailActivityJI : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail_ji)
        var detail: DetailData? = intent.getParcelableExtra("DATA")
        if (detail == null) {
            Toast.makeText(this, R.string.str_no_data, Toast.LENGTH_SHORT).show()
            finish()
            return
        }
    }
}