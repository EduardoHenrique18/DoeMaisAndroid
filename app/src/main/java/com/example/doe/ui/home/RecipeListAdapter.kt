package com.example.doe.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doe.remote.response.CreateRecipeDetailResponse

class RecipeListAdapter(
    private val listener: RecipeItemListener
) : RecyclerView.Adapter<RecipeItemView>() {
    private var data: List<CreateRecipeDetailResponse> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeItemView {
        return RecipeItemView.createView(parent, listener)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecipeItemView, position: Int) {
        holder.bindData(data[position])
    }

    fun updateData(recipes: List<CreateRecipeDetailResponse>) {
        data = recipes
        notifyDataSetChanged()
    }
}