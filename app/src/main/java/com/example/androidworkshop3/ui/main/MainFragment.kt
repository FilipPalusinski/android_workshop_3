package com.example.androidworkshop3.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidworkshop3.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(R.layout.main_fragment) {

    private val viewModel: MainViewModel by viewModels()
    private val itemAdapter = ItemAdapter(::onLikeClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshButton.setOnClickListener {
            viewModel.refreshData()
        }
        itemsRecyclerView.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.data.observe(viewLifecycleOwner) { itemAdapter.submitList(it) }
    }

    override fun onDestroyView() {
        itemsRecyclerView.adapter = null
        super.onDestroyView()
    }

    private fun onLikeClick(item: Item) {
        Toast.makeText(context, "Like: ${item.id}", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
