package com.example.doe.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.doe.R
import com.example.doe.remote.response.CreateRecipeDetailResponse


class RecipeItemView(
    itemView: View,
    private val listener: RecipeItemListener?
) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun createView(parent: ViewGroup, listener: RecipeItemListener? = null): RecipeItemView {
            return RecipeItemView(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_recipe, parent, false),
                listener
            )
        }
    }

    fun bindData(recipeInfo: CreateRecipeDetailResponse) {
        itemView.setOnClickListener {
            listener?.onClickItem(recipeInfo)
        }

        itemView.findViewById<ImageView>(R.id.image_delete)?.setOnClickListener {
            listener?.onClickDeleteItem(recipeInfo)
        }

        itemView.findViewById<ImageView>(R.id.image_edit)?.setOnClickListener {
            listener?.onClickEditItem(recipeInfo)
        }

        itemView.findViewById<TextView>(R.id.text_description)?.text = recipeInfo.description
    }
}