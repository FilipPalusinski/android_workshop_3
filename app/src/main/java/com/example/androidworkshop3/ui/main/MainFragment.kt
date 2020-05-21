package com.example.androidworkshop3.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidworkshop3.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(R.layout.main_fragment) {

    private val viewModel: MainViewModel by viewModels()
    private val itemAdapter = ItemAdapter()

    private val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.START or ItemTouchHelper.END
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ) = true

        override fun onMoved(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            fromPos: Int,
            target: RecyclerView.ViewHolder,
            toPos: Int,
            x: Int,
            y: Int
        ) {
            val newList = itemAdapter.currentList.toMutableList()
            val movedItem = newList[fromPos]
            val targetItem = newList[toPos]
            newList[fromPos] = targetItem
            newList[toPos] = movedItem
            itemAdapter.submitList(newList)
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val newList = itemAdapter.currentList.toMutableList().apply {
                removeAt(viewHolder.adapterPosition)
            }
            itemAdapter.submitList(newList)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshButton.setOnClickListener {
            viewModel.refreshData()
        }
        itemsRecyclerView.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator().apply { supportsChangeAnimations = false }
            ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(this)
        }

        viewModel.data.observe(viewLifecycleOwner) { itemAdapter.submitList(it) }
    }

    override fun onDestroyView() {
        itemsRecyclerView.adapter = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
