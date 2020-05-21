package com.example.androidworkshop3.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private val _data = MutableLiveData<List<Item>>()
    val data: LiveData<List<Item>> = _data

    init {
        refreshData()
    }

    fun refreshData() {
        _data.value = List(30) { index ->
            val id = index + 1
            Item(
                id = id,
                primaryText = "Item $id",
                secondaryText = if (Random.nextBoolean()) "Secondary $id" else null,
                isFeatured = id % 3 == 0
            )
        }.filter { Random.nextInt(10) < 9 }
    }
}
