package com.example.teamproject10

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.teamproject10.data.SignMember
import com.example.teamproject10.data.UserData

class SignUpActivity : AppCompatActivity() {
    private var isCheckValid = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextId = findViewById<EditText>(R.id.editTextId)
        val editTextPw = findViewById<EditText>(R.id.editTextPw)
        val btnJoin = findViewById<Button>(R.id.buttonLoginActivity)
        val btnValid = findViewById<Button>(R.id.btn_valid_id)

        btnJoin.setOnClickListener {
            // 공백이 포함되었을 경우 대비하여 trim메소드 활용하여 문자열 다루기
            val name = editTextName.text.toString()
            val id = editTextId.text.toString()
            val pw = editTextPw.text.toString()

            if (!isCheckValid) {
                Toast.makeText(applicationContext, R.string.str_valid_check_please, Toast.LENGTH_SHORT).show()
            }
            else if (name.isNotEmpty() && id.isNotEmpty() && pw.isNotEmpty()) {
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("name", name)
                intent.putExtra("id", id)
                intent.putExtra("pw", pw)

                SignMember.signMemberList.add(UserData(name, id, pw))

                setResult(RESULT_OK, intent)
                finish()
            }
            else {
                Toast.makeText(applicationContext, R.string.str_toast_check, Toast.LENGTH_SHORT).show()
            }
        }

        btnValid.setOnClickListener {
            checkMember(editTextId.text.toString())
        }
    }

    private fun checkMember(id: String) {
        if (isExistMember(id)) {
            Toast.makeText(applicationContext, R.string.str_valid_check_existid, Toast.LENGTH_SHORT).show()
            isCheckValid = false
        }
        else if (id.isEmpty()){
            Toast.makeText(applicationContext,R.string.str_toast_check_id, Toast.LENGTH_SHORT).show()
            isCheckValid = false
        }
        else {
            Toast.makeText(applicationContext, R.string.str_valid_check_id_success, Toast.LENGTH_SHORT).show()
            isCheckValid = true
        }
    }

    /**
     * 회원인지 체크하는 함수
     */
    private fun isExistMember(id: String): Boolean {
        //조건에 맞는 회원들을 찾는 코드
        val result = SignMember.signMemberList.filter {
            it.id == id
        }

        return result.isNotEmpty()
    }
}