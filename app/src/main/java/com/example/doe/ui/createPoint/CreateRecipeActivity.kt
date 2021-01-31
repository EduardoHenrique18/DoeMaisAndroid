package com.example.doe.ui.createRecipe

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.doe.R
import com.example.doe.data.local.local.SharedPreferenceUserDataSource
import com.example.doe.device.DeviceConnFactory
import com.example.doe.device.DeviceWifiController
import com.example.doe.domain.usecase.CheckIntentConnectionUseCase
import com.example.doe.domain.usecase.CreateRecipeUseCase
import com.example.doe.remote.CreateRecipeService
import com.example.doe.remote.RestWebService
import com.example.doe.ui.createPoint.CreateRecipeContract


class CreateRecipeActivity : AppCompatActivity(), CreateRecipeContract.CreateRecipeView {
    private lateinit var presenter: CreateRecipeContract.CreateRecipePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)

        presenter = CreateRecipePresenter(
            this,
            CreateRecipeUseCase(
                CreateRecipeService(RestWebService().api),
                SharedPreferenceUserDataSource(this)
            ),
            CheckIntentConnectionUseCase(
                DeviceWifiController(this),
                DeviceConnFactory.createConnectivityController(this)
            ),
            SharedPreferenceUserDataSource(this)
        )

        findViewById<TextView>(R.id.button_recipe_create)?.setOnClickListener {
            presenter.onRegisterRecipeClicked()
        }
    }

    override fun getDescription(): String = findViewById<EditText>(R.id.createRecipe_description).text.toString()

    override fun getPrepareMethod(): String = findViewById<EditText>(R.id.createRecipe_prepareMethod).text.toString()

    override fun getYield(): String = findViewById<EditText>(R.id.CreateRecipe_yield).text.toString()

    override fun getProduct(): String = findViewById<EditText>(R.id.CreateRecipe_product).text.toString()

    override fun showEmptyInfoError() {
        Toast.makeText(
            this,
            getString(R.string.text_empty_user_info_error),
            Toast.LENGTH_LONG).show()
    }

    override fun showNoInternet() {
        Toast.makeText(
            this,
            getString(R.string.text_no_internet_error),
            Toast.LENGTH_LONG).show()
    }

    override fun recipeCreated() {
        Toast.makeText(
            this,
            getString(R.string.recipe_created),
            Toast.LENGTH_LONG).show()
    }

}