package com.example.androidworkshop3.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidworkshop3.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(R.layout.main_fragment) {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refreshButton.setOnClickListener{ viewModel.refreshData()}

        val itemsAdapter = ItemsAdapter()
        itemsRecyclerView.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = itemsAdapter
        }

        viewModel.data.observe(viewLifecycleOwner){ items ->
             itemsAdapter.items = items
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
