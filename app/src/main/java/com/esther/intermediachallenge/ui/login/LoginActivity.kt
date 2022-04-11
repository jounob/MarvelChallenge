package com.esther.intermediachallenge.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.esther.intermediachallenge.databinding.ActivityLoginBinding
import com.esther.intermediachallenge.ui.main.MainActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //facebook Login
        binding.loginButtonFacebook.setOnClickListener{
            loginWithFacebook()
        }
    }

    fun showError(error:String="Error"){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    //Login with Facebook
    private fun loginWithFacebook(){
        LoginManager.getInstance().logInWithReadPermissions(this, callbackManager, listOf("email"))
        LoginManager.getInstance().registerCallback(callbackManager,
            object: FacebookCallback<LoginResult>{
                override fun onCancel() {
                    showError("you canceled your login")
                }

                override fun onError(error: FacebookException) {
                    showError("You don't have a facebook account")
                }

                override fun onSuccess(result: LoginResult) {
                    result.let{ it ->
                        val token = it.accessToken
                        val credential = FacebookAuthProvider
                            .getCredential(token.token)
                        FirebaseAuth.getInstance()
                            .signInWithCredential(credential).addOnCompleteListener {
                                if(it.isSuccessful){
                                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                    finish()
                                } else{
                                    showError("you can't connect whit your facebook accout please verify. Try again")
                                }
                            }
                    }
                }

            })
    }
}
















//key hash =Hzh4P5G3bPA2Dy/Oeox+1JtWZgU=

// zY00cczs2SG28RTn1bZQvsCftvI=