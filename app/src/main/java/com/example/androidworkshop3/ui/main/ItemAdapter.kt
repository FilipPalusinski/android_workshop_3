package com.example.androidworkshop3.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidworkshop3.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item.*

class ItemAdapter(private val onLikeClick: (Item) -> Unit) : ListAdapter<Item, ItemViewHolder>(DiffCallback) {

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isFeatured) R.layout.featured_item else R.layout.item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item -> ItemViewHolder.Default(itemView, onLikeClick)
            R.layout.featured_item -> ItemViewHolder.Featured(itemView, onLikeClick)
            else -> error("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }
}

sealed class ItemViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    abstract fun bind(item: Item)

    class Default(
        containerView: View,
        onLikeClick: (Item) -> Unit
    ) : ItemViewHolder(containerView) {
        lateinit var item: Item
        init {
            likeButton.setOnClickListener { onLikeClick(item) }
        }

        override fun bind(item: Item) {
            this.item = item
            primaryText.text = item.primaryText
            secondaryText.text = item.secondaryText
        }
    }

    class Featured(
        containerView: View,
        onLikeClick: (Item) -> Unit
    ) : ItemViewHolder(containerView) {
        lateinit var item: Item
        init {
            likeButton.setOnClickListener { onLikeClick(item) }
        }

        override fun bind(item: Item) {
            this.item = item
            primaryText.text = item.primaryText
            secondaryText.text = item.secondaryText
        }
    }
}
