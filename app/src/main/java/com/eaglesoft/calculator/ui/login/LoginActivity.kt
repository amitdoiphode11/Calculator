package com.eaglesoft.calculator.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eaglesoft.calculator.R
import com.eaglesoft.calculator.ui.calc.MainActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {


    companion object {
        private val TAG = "LoginActivity"
        private const val RC_SIGN_IN = 1

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (FirebaseAuth.getInstance().currentUser != null) {
            goToHome()
        } else {
            firebaseAuth()
        }
    }


    private fun firebaseAuth() {
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
        )

        // Create and launch sign-in intent
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                Toast.makeText(this, getString(R.string.success_login), Toast.LENGTH_SHORT).show()
                goToHome()
            } else {
                Toast.makeText(this, getString(R.string.error_login), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToHome() {
        intent = MainActivity.getStartIntent(this)
        startActivity(intent)
        finish()
    }
}
