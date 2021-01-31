package com.example.doe.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doe.R
import com.example.doe.data.local.local.SharedPreferenceUserDataSource
import com.example.doe.domain.usecase.DeleteRecipeUseCase
import com.example.doe.domain.usecase.SearchRecipeUseCase
import com.example.doe.remote.DeleteRecipeService
import com.example.doe.remote.RestWebService
import com.example.doe.remote.SearchRecipeService
import com.example.doe.remote.response.CreateRecipeDetailResponse
import com.example.doe.ui.createRecipe.CreateRecipeActivity
import com.example.doe.ui.login.LoginActivity


class HomeActivity : AppCompatActivity(), HomeContract.HomeView, RecipeItemListener {
    private lateinit var presenter: HomeContract.HomePresenter
    private lateinit var recipeListAdapter: RecipeListAdapter

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupRecipeListView()

        presenter = HomePresenter(
            this,
            SearchRecipeUseCase(
                SearchRecipeService(RestWebService().api)
            ),
            DeleteRecipeUseCase(
                DeleteRecipeService(RestWebService().api)
            ),
            SharedPreferenceUserDataSource(this)
        )

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                100);
        }

        findViewById<ImageView>(R.id.create_recipe_button)?.setOnClickListener {
            presenter.onCreateRecipeClicked()
        }

        findViewById<ImageView>(R.id.home_logout)?.setOnClickListener {
            presenter.onLogoutClicked()
        }

        presenter.searchRecipe()

        presenter.recipeListObserve()?.observe(this, Observer {
            recipeListAdapter.updateData(it)
        })
    }

    private fun setupRecipeListView() {
        val recyclerView = findViewById<RecyclerView>(R.id.list_recipes)
        recyclerView.layoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false)
        recipeListAdapter = RecipeListAdapter(this)
        recyclerView.adapter = recipeListAdapter
    }

    override fun showRecipeList(recipes: List<CreateRecipeDetailResponse>) {
        recipeListAdapter.updateData(recipes)
    }

    override fun showRecipeDeleted() {
        Toast.makeText(
            this,
            getString(R.string.recipe_deleted),
            Toast.LENGTH_LONG).show()
    }

    override fun openRegisterRecipe() {
        var intent = Intent(this, CreateRecipeActivity::class.java)
        startActivity(intent)
    }

    override fun logout() {
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onClickItem(recipeInfo: CreateRecipeDetailResponse) {
        presenter.onClickedItem(recipeInfo)
    }

    override fun onClickDeleteItem(recipeInfo: CreateRecipeDetailResponse) {
        presenter.onClickedDeleteItemOption(recipeInfo)
    }

    override fun onClickEditItem(recipeInfo: CreateRecipeDetailResponse) {
        presenter.onClickedEditItemOption(recipeInfo)
    }
}