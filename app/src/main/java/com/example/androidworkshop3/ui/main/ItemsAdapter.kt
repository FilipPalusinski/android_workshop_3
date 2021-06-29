package com.example.androidworkshop3.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidworkshop3.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.default_item.*

class ItemsAdapter: RecyclerView.Adapter<ItemViewHolder>() {
    var items: List<Item> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        return if(items[position].isFeatured) R.layout.featured_item else R.layout.default_item
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return when(viewType) {
            R.layout.default_item -> ItemViewHolder.Default(itemView)
            R.layout.featured_item -> ItemViewHolder.Featured(itemView)
            else -> error("$viewType is not supported")
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount()  = items.size
}

sealed class ItemViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
   abstract fun bind(item: Item)



    class Default(containerView: View): ItemViewHolder(containerView){
        override fun bind(item: Item) {
            primaryText.text = item.primaryText
            secondaryText.text = item.secondaryText
        }
    }
    class Featured(containerView: View): ItemViewHolder(containerView){
        override fun bind(item: Item) {
            primaryText.text = item.primaryText
            secondaryText.text = item.secondaryText
        }
    }
}