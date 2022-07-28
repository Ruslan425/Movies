package ru.romazanov.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.romazanov.movies.data.models.Result
import ru.romazanov.movies.databinding.ActivityMainBinding
import ru.romazanov.movies.recycler.MyAdapter
import ru.romazanov.movies.recycler.MyListener

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private val adapter: MyAdapter by lazy {
        MyAdapter()
    }
    private lateinit var dataList: LiveData<List<Result>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFirstList()
        dataList = viewModel.resultList
        setTheme(R.style.Theme_Movies)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        val dataObserver = Observer<List<Result>> { list ->
            adapter.setList(list)
        }

        viewModel.resultList.observe(this, dataObserver)
    }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerView
        val linearLayoutManager = LinearLayoutManager(application)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addOnScrollListener(
            MyListener(viewModel)
        )
    }

}