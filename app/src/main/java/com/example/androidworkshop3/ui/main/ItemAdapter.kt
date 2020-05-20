package com.example.androidworkshop3.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidworkshop3.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item.*

class ItemAdapter : RecyclerView.Adapter<ItemViewHolder>() {
    var items = emptyList<Item>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].isFeatured) R.layout.featured_item else R.layout.item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item -> ItemViewHolder.Default(itemView)
            R.layout.featured_item -> ItemViewHolder.Featured(itemView)
            else -> error("Unknown view type: $viewType")
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

sealed class ItemViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    abstract fun bind(item: Item)

    class Default(containerView: View) : ItemViewHolder(containerView) {
        override fun bind(item: Item) {
            primaryText.text = item.primaryText
            secondaryText.text = item.secondaryText
        }
    }

    class Featured(containerView: View) : ItemViewHolder(containerView) {
        override fun bind(item: Item) {
            primaryText.text = item.primaryText
            secondaryText.text = item.secondaryText
        }
    }
}
