package com.example.teamproject10

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
            val editTextName = findViewById<EditText>(R.id.editTextName)
            val editTextId = findViewById<EditText>(R.id.editTextId)
            val editTextPw = findViewById<EditText>(R.id.editTextPw)
            val btnJoin = findViewById<Button>(R.id.buttonLoginActivity)
        btnJoin.setOnClickListener {
            val name = editTextName.text.toString()
            val id = editTextId.text.toString()
            val pw = editTextPw.text.toString()

            if(name.isNotEmpty()&&id.isNotEmpty()&&pw.isNotEmpty()){
                val intent = Intent(this,LoginActivity::class.java)
                intent.putExtra("id",id)
                intent.putExtra("pw",pw)

                setResult(RESULT_OK,intent)
                finish()
            }else {
                Toast.makeText(applicationContext,"입력되지 않은 정보가 있습니다.",Toast.LENGTH_SHORT).show()
            }

        }

    }
}