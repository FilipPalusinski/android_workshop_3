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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }
}


class ItemViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(item: Item) {
        primaryText.text = item.primaryText
        secondaryText.text = item.secondaryText
    }
}
