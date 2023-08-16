package com.example.teamproject10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class LoginActivity : AppCompatActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val editTextId = findViewById<EditText>(R.id.editTextLogId)
        val editTextPw = findViewById<EditText>(R.id.editTextPw)
        val btnLogin = findViewById<Button>(R.id.buttonMainActivity)
        val btnJoin = findViewById<Button>(R.id.buttonSignUpActivity)

        btnLogin.setOnClickListener {
            val id = editTextId.text.toString()
            val pw = editTextPw.text.toString()

            if (id.isNotEmpty() && pw.isNotEmpty()) {
                val intent = Intent(this,MainActivity::class.java)
                intent.putExtra("Id",id)
                startActivity(intent)
                Toast.makeText(applicationContext,"로그인 성공",Toast.LENGTH_SHORT).show()
           } else{
               Toast.makeText(applicationContext,"아이디/비밀번호를 확인해주세요.",Toast.LENGTH_SHORT).show()
           }
        }
        btnJoin.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)

            resultLauncher.launch(intent)
        }

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val id = it.data?.getStringExtra("id")?:""
                val pw = it.data?.getStringExtra("pw")?:""
                editTextId.setText(id)
                editTextPw.setText(pw)
            }
        }

    }
}