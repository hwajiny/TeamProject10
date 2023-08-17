package com.example.teamproject10

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.teamproject10.data.UserData

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
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                intent.putExtra("Id",id)
//                startActivity(intent) //이거하믄 새로 만듬

                //가입한 회원정보와 일치하면 정보를 맴버객체에 담아주고 메인으로 이동시켜야 한다.
                //지금은 임시로 무조건 이동
                UserData.id = id
                UserData.pw = pw
                setResult(Activity.RESULT_OK, intent)

                Toast.makeText(applicationContext,"로그인 성공",Toast.LENGTH_SHORT).show()

                finish()
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