package ru.romazanov.movies.recycler

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import ru.romazanov.movies.MainActivityViewModel

class MyListener(val vm: MainActivityViewModel) : RecyclerView.OnScrollListener() {
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (!recyclerView.canScrollVertically(1)) {
            vm.getNextPage()
        }
    }
}