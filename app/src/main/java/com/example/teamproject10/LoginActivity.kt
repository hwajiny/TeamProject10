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
import com.example.teamproject10.data.SignMember

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
//            공백이 포함되었을 경우 대비하여 trim메소드 활용하여 문자열 다루기

            val id = editTextId.text.toString().trim()
            val pw = editTextPw.text.toString().trim()

            // isMember함수를 통해서 회원의 로그인 정보가 맞는지 체크
            if (id.isNotEmpty() && pw.isNotEmpty() && isMember(id, pw)) {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)

                setResult(Activity.RESULT_OK, intent)

                Toast.makeText(applicationContext, R.string.str_toast_login_success, Toast.LENGTH_SHORT).show()
                finish()
            } else if (id.isNotEmpty() && pw.isEmpty()) {
                Toast.makeText(applicationContext, R.string.str_toast_check_pw, Toast.LENGTH_SHORT).show()
            } else if (id.isEmpty() && pw.isNotEmpty()) {
                Toast.makeText(applicationContext, R.string.str_toast_check_id, Toast.LENGTH_SHORT).show()
            } else if (id.isEmpty() && pw.isEmpty()) {
                Toast.makeText(applicationContext, R.string.str_toast_check_idpw, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, R.string.str_toast_check_user, Toast.LENGTH_SHORT).show()
            }
        }

        btnJoin.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)

            resultLauncher.launch(intent)
        }

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val id = it.data?.getStringExtra("id") ?: ""
                    val pw = it.data?.getStringExtra("pw") ?: ""
                    editTextId.setText(id)
                    editTextPw.setText(pw)
                }
            }
    }

    /**
     * 회원인지 체크하는 함수
     */
    private fun isMember(id: String, pw: String): Boolean {
        //조건에 맞는 회원들을 찾는 코드
        val result = SignMember.signMemberList.filter {
            it.id == id && it.pw == pw
        }

        if (result.isNotEmpty()) {
            SignMember.currentUser = result.first()
            return true
        } else {
            return false
        }
    }

}