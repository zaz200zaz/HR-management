package com.example.kotlinviewkensyuu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var inputEmail:EditText=findViewById(R.id.inputEmail)
        var inputPassword:EditText=findViewById(R.id.inputPassword)
        val btnLogin:Button=findViewById(R.id.btnLogin)
        val gotoRegister:TextView=findViewById(R.id.gotoRegister)


        //login機能
        btnLogin.setOnClickListener(View.OnClickListener {
            loginHandle()
        })

        //新規登録機能
        gotoRegister.setOnClickListener(View.OnClickListener {

        })

    }
    fun loginHandle(){
        var email : String = inputEmail.text.toString()
        var password : String = inputPassword.text.toString()

        if (email=="admin@gmail.com"&&password == "123456"){

            Toast.makeText(this,"ログイン成功",Toast.LENGTH_LONG).show()
            val intent=Intent(this,List::class.java)
            startActivity(intent)
        }
    }
    fun signUp(){
        val intern = Intent(this,Register::class.java)
        startActivity(intern)
    }
    fun new(){

    }
}