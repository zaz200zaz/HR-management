package com.example.kotlinviewkensyuu

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnLogin:Button=findViewById(R.id.btnLogin)
        val gotoRegister:TextView=findViewById(R.id.gotoRegister)

        //login機能
        btnLogin.setOnClickListener(View.OnClickListener {
            loginHandle()
        })

        //新規登録機能
        gotoRegister.setOnClickListener(View.OnClickListener {
            val intern = Intent(this,Register::class.java)
            startActivity(intern)
        })
    }

    fun loginHandle() {
        var email: String = inputEmail.editableText.toString()
        var password: String = inputPassword.editableText.toString()

        if (email.isEmpty() || !email.contains("gmail")) {
            Toast.makeText(this, "Eメールを確認してください", Toast.LENGTH_LONG).show()
        }else if (password.isEmpty()||password.length<6) {
            Toast.makeText(this, "パスワードを確認してください", Toast.LENGTH_LONG).show()
        }else{
            Firebase.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "ログイン成功しました", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, List::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "ログイン失敗しました", Toast.LENGTH_LONG).show()

                    }
                }
        }
    }



    fun new(){

    }
}