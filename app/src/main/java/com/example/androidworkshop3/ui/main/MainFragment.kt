package com.example.androidworkshop3.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.*
import com.example.androidworkshop3.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(R.layout.main_fragment) {

    private val viewModel: MainViewModel by viewModels()

    val itemsAdapter = ItemsAdapter()

    private val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper. DOWN, ItemTouchHelper.START or ItemTouchHelper.END){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val newList = itemsAdapter.currentList.toMutableList()
            newList.removeAt(viewHolder.bindingAdapterPosition)
            itemsAdapter.submitList(newList)
        }

        override fun onMoved(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            fromPos: Int,
            target: RecyclerView.ViewHolder,
            toPos: Int,
            x: Int,
            y: Int
        ) {
            val newList = itemsAdapter.currentList.toMutableList()
            val movedItem = newList[fromPos]
            val targetItem = newList[toPos]
            newList[fromPos] = targetItem
            newList[toPos] = movedItem
            itemsAdapter.submitList(newList)
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refreshButton.setOnClickListener{ viewModel.refreshData()}


        itemsRecyclerView.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = itemsAdapter
            itemAnimator = DefaultItemAnimator().apply { supportsChangeAnimations = false }
            ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(this)
            LinearSnapHelper().attachToRecyclerView(this)
        }


        viewModel.data.observe(viewLifecycleOwner){ items ->
             itemsAdapter.submitList(items)
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
