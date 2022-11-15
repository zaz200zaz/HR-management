package com.example.kotlinviewkensyuu

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var FirebaseUserID: String = ""

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mAuth = FirebaseAuth.getInstance()
        AlreadyHaveAccount.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        })
        btnRegister.setOnClickListener(View.OnClickListener
        {

            check(inputEmail.text.toString().trim(),InputConfirmEmail.text.toString().trim(),
                inputPasswords.text.toString().trim(),InputConfirmPassword.text.toString().trim(),)
        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun check(trim: String, trim1: String, trim2: String, trim3: String) {
        var checkEmail: Boolean = false
        var checkPassword: Boolean = false
       if (trim.isEmpty()||trim1.isEmpty()){
           Toast.makeText(this,"メールエラー",Toast.LENGTH_LONG).show()
           inputEmail.setHintTextColor(resources.getColor(android.R.color.holo_red_dark))
           InputConfirmEmail.setHintTextColor(resources.getColor(android.R.color.holo_red_dark))
//           inputEmail.setTextColor(resources.getColor(android.R.color.holo_red_dark))
           checkEmail = false
       }else{

           if (trim.equals(trim1)){
               InputConfirmEmail.setTextColor(resources.getColor(R.color.black))
               inputEmail.setTextColor(resources.getColor(R.color.black))
               checkEmail = true
           }else{
               InputConfirmEmail.setTextColor(resources.getColor(android.R.color.holo_red_dark))
               inputEmail.setTextColor(resources.getColor(android.R.color.holo_red_dark))

               checkEmail = false
           }
       }

        if (trim2.isEmpty()||trim3.isEmpty()){
            Toast.makeText(this,"パスワードエラー",Toast.LENGTH_LONG).show()
            inputPasswords.setHintTextColor(resources.getColor(android.R.color.holo_red_dark))
            InputConfirmPassword.setHintTextColor(resources.getColor(android.R.color.holo_red_dark))
            checkPassword = false
        }else{

            if (trim2.equals(trim3)){
                inputPasswords.setTextColor(resources.getColor(R.color.black))
                InputConfirmPassword.setTextColor(resources.getColor(R.color.black))
                checkPassword = true
            }else{
                inputPasswords.setTextColor(resources.getColor(android.R.color.holo_red_dark))
                InputConfirmPassword.setTextColor(resources.getColor(android.R.color.holo_red_dark))
                checkPassword = false
            }
        }
        if (checkEmail && checkPassword){


            mAuth.createUserWithEmailAndPassword(trim,trim2).addOnCompleteListener{task ->
                if (task.isSuccessful){
                    FirebaseUserID = mAuth.currentUser!!.uid
                    refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(FirebaseUserID)

                    val userHasMap = HashMap<String, Any>()
                    userHasMap["uid"] = FirebaseUserID
                    userHasMap["email"] = trim
                    userHasMap["password"] = trim2
                    refUsers.updateChildren(userHasMap)
                        .addOnCompleteListener{task ->
                            if (task.isSuccessful){
                                Toast.makeText(this,"登録成功",Toast.LENGTH_LONG).show()
                                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
                                finish()
                            }
                        }

                    //hai
                }else{
                    Log.d(TAG, "check22: "+task.exception!!.message.toString())
                    Toast.makeText(this,"エラー：" + task.exception!!.message.toString(),Toast.LENGTH_LONG).show()
                }
            }

            }

    }

}

