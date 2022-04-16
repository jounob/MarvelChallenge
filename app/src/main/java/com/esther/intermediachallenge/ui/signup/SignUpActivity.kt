package com.esther.intermediachallenge.ui.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import com.esther.intermediachallenge.databinding.ActivitySignUpBinding
import com.esther.intermediachallenge.ui.login.LoginActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private val signUpViewModel: SignUpViewModel by viewModels()
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        onFieldsTextChange()
        observeFieldsState()
        binding.tvSignUpButton.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
        }

    }

    private fun showToast(msg: String = "Error") {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
//            reload()
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
//                    updateUI(user)
                } else {
                    showSnackBar("YOU ALREADY HAVE AN ACCOUNT WITH THIS EMAIL. GO TO LOGIN")
//                    updateUI(null)
                }

            }
    }

    private fun onFieldsTextChange() {
        binding.apply {
            edtName.doAfterTextChanged {
                signUpViewModel.updateSignUpState(
                    edtName.text.toString(),
                    edtEmail.text.toString(),
                    edtPassword.text.toString(),
                    edtConfirmedPassword.text.toString()
                )
            }
            edtEmail.doAfterTextChanged {
                signUpViewModel.updateSignUpState(
                    edtName.text.toString(),
                    edtEmail.text.toString(),
                    edtPassword.text.toString(),
                    edtConfirmedPassword.text.toString()
                )
            }
            edtPassword.doAfterTextChanged {
                signUpViewModel.updateSignUpState(
                    edtName.text.toString(),
                    edtEmail.text.toString(),
                    edtPassword.text.toString(),
                    edtConfirmedPassword.text.toString()
                )
            }
            edtConfirmedPassword.doAfterTextChanged {
                signUpViewModel.updateSignUpState(
                    edtName.text.toString(),
                    edtEmail.text.toString(),
                    edtPassword.text.toString(),
                    edtConfirmedPassword.text.toString()
                )
            }
        }
    }

    private fun observeFieldsState() {
        signUpViewModel.signUpState.observe(this) {
            with(binding) {
                btnSignUp.isEnabled = it.isAllFieldValid
                when {
                    it.isUserNameValid && edtName.text?.isNotBlank() == true -> edtName.error =
                        "username error"
                    it.isEmailValid && edtEmail.text?.isNotBlank() == true -> edtEmail.error =
                        "Email not correct"
                    it.isPasswordValid && edtPassword.text?.isNotBlank() == true -> edtPassword.error =
                        "Password not correct"
                    edtConfirmedPassword != edtPassword && it.isConfirmedPasswordValid -> edtConfirmedPassword.error =
                        "Don't match Password"
                    else -> {
                        btnSignUp.setOnClickListener {
                            createAccount(
                                edtEmail.text.toString(),
                                edtPassword.text.toString()
                            )
                            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))

                        }
                    }
                }
            }

        }
    }
    fun showSnackBar(msg: String) {
        val contextView = binding.btnSignUp
        Snackbar.make(contextView, msg, Snackbar.LENGTH_SHORT)
            .show()
    }




    companion object {
        private const val TAG = "EmailPassword"
    }


}