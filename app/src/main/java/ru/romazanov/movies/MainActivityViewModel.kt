package ru.romazanov.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.romazanov.movies.data.models.Answer
import ru.romazanov.movies.data.models.Result
import ru.romazanov.movies.data.reposotiry.Repository
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private var page = 20
    private lateinit var answer: Answer
    private var allList: List<Result> = mutableListOf()
    private val _resultList: MutableLiveData<List<Result>> by lazy {
        MutableLiveData()
    }
    val resultList = _resultList

    fun getFirstList(){
        viewModelScope.launch {
            answer = repository.doNetworkCall("")
            allList += answer.results
            _resultList.postValue(allList)
        }
    }

    fun getNextPage(){
        viewModelScope.launch {
            page += 20
            answer = repository.doNetworkCall(page.toString())
            allList += answer.results
            _resultList.postValue(allList)
        }
    }

}