package com.example.doe.ui.login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.doe.R

class LoginActivity : AppCompatActivity(), LoginContract.LoginView {
    private lateinit var presenter: LoginContract.LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(
            this
        )

        findViewById<Button>(R.id.button_login)?.setOnClickListener {
            presenter.onLoginClicked()
        }
    }

    override fun getUserName(): String = findViewById<EditText>(R.id.text_email).text.toString()
    override fun getUserPassword(): String = findViewById<EditText>(R.id.text_password).text.toString()

    override fun openHomeScreen() {
        //startActivity(Intent(this, HomeActivity::class.java))
    }

    override fun showError() {
        Toast.makeText(this, "Login Error!!!", Toast.LENGTH_LONG).show()
    }

    override fun showEmptyInfoError() {
        /*Toast.makeText(
            this,
            getString(R.string.text_empty_user_info_error),
            Toast.LENGTH_LONG).show()*/
    }

    override fun showUnauthUserError() {
        /*Toast.makeText(
            this,
            getString(R.string.text_unauthenticated_user_error),
            Toast.LENGTH_LONG).show()*/
    }

    override fun showNoInternet() {
        /*Toast.makeText(
            this,
            getString(R.string.text_no_internet_error),
            Toast.LENGTH_LONG).show()*/
    }
}