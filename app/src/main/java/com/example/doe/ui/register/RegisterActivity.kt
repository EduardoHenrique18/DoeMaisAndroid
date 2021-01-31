package com.example.doe.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.doe.R
import com.example.doe.device.DeviceConnFactory
import com.example.doe.device.DeviceWifiController
import com.example.doe.domain.usecase.CheckIntentConnectionUseCase
import com.example.doe.domain.usecase.RegisterUseCase
import com.example.doe.remote.RegisterUserService
import com.example.doe.remote.RestWebService

class RegisterActivity : AppCompatActivity(), RegisterContract.RegisterView {
    private lateinit var presenter: RegisterContract.RegisterPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = RegisterPresenter(
            this,
            RegisterUseCase(
                RegisterUserService(RestWebService().api)
            ),
            CheckIntentConnectionUseCase(
                DeviceWifiController(this),
                DeviceConnFactory.createConnectivityController(this)
            )
        )

        findViewById<Button>(R.id.button_register)?.setOnClickListener {
            presenter.onRegisterClicked()
        }
    }

    override fun getEmail(): String = findViewById<EditText>(R.id.register_email).text.toString()

    override fun getUserPassword(): String = findViewById<EditText>(R.id.register_password).text.toString()

    override fun getName(): String = findViewById<EditText>(R.id.register_name).text.toString()

    override fun showNoInternet() {
        Toast.makeText(
            this,
            getString(R.string.text_no_internet_error),
            Toast.LENGTH_LONG).show()
    }

    override fun showUserRegistered() {
        Toast.makeText(
            this,
            getString(R.string.user_registered),
            Toast.LENGTH_LONG).show()
    }

    override fun showEmptyInfoError() {
        Toast.makeText(
            this,
            getString(R.string.text_empty_register_info_error),
            Toast.LENGTH_LONG).show()
    }
}