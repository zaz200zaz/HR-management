package com.example.kotlinviewkensyuu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var FirebaseUserID: String = ""
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
            signUp()
        })
    }
    fun loginHandle() {
        mAuth = FirebaseAuth.getInstance()

        var email: String = inputEmail.text.toString()
        var password: String = inputPassword.text.toString()
        if (email ==""){
            Toast.makeText(this, "メール 入力してくださいよ", Toast.LENGTH_LONG).show()
        }else if (password==""){
            Toast.makeText(this, "パスワード 入力してくださいよ", Toast.LENGTH_LONG).show()
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener{task ->
                if (task.isSuccessful){
                    Toast.makeText(this,"登録成功",Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,List::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
                    finish()
//                    Toast.makeText(this,"エラー：" + task.exception!!.message.toString(),Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"エラー：" + task.exception!!.message.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }

        if (email == "admin@gmail.com" && password == "123456") {

            Toast.makeText(this, "ログイン成功", Toast.LENGTH_LONG).show()
            val intent = Intent(this, List::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "ログイン失敗", Toast.LENGTH_LONG).show()
        }
    }
    fun signUp(){
        val intern = Intent(this,Register::class.java)
        startActivity(intern)
    }


    fun new(){

    }
}