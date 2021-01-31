package com.example.doe.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.doe.MyApplication
import com.example.doe.R
import com.example.doe.data.local.local.SharedPreferenceUserDataSource
import com.example.doe.device.DeviceConnFactory
import com.example.doe.device.DeviceWifiController
import com.example.doe.domain.usecase.CheckIntentConnectionUseCase
import com.example.doe.domain.usecase.LoginUseCase
import com.example.doe.remote.RemoveAuthUserService
import com.example.doe.remote.RestWebService
import com.example.doe.ui.home.HomeActivity
import com.example.doe.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity(), LoginContract.LoginView {
    private lateinit var presenter: LoginContract.LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(
            this,
            LoginUseCase(
                RemoveAuthUserService(RestWebService().api),
                SharedPreferenceUserDataSource(this)
            ),
            CheckIntentConnectionUseCase(
                DeviceWifiController(this),
                DeviceConnFactory.createConnectivityController(this)
            )
        )

        findViewById<Button>(R.id.button_login)?.setOnClickListener {
            presenter.onLoginClicked()
        }

        findViewById<TextView>(R.id.text_button_register)?.setOnClickListener {
            presenter.onRegisterClicked()
        }
    }

    override fun getEmail(): String = findViewById<EditText>(R.id.user_email).text.toString()
    override fun getUserPassword(): String = findViewById<EditText>(R.id.user_password).text.toString()

    override fun openHomeScreen() {
        var intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun openRegisterScreen() {
        var intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun showError() {
        Toast.makeText(
            this,
            "Login Error!!!",
            Toast.LENGTH_LONG).show()
    }

    override fun showEmptyInfoError() {
        Toast.makeText(
            this,
            getString(R.string.text_empty_user_info_error),
            Toast.LENGTH_LONG).show()
    }

    override fun showUnauthUserError() {
        Toast.makeText(
            this,
            getString(R.string.text_unauthenticated_user_error),
            Toast.LENGTH_LONG).show()
    }

    override fun showNoInternet() {
        Toast.makeText(
            this,
            getString(R.string.text_no_internet_error),
            Toast.LENGTH_LONG).show()
    }

}