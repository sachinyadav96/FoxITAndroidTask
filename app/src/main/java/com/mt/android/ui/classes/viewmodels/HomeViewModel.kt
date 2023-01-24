package com.mt.android.ui.classes.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mt.android.data.model.MainList
import com.mt.android.data.repository.RetrofitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val retrofitRepository: RetrofitRepository = RetrofitRepository()

    private var userLiveData: MutableLiveData<MainList> = MutableLiveData()


    fun getUserDataList(): MutableLiveData<MainList> {
        return userLiveData
    }

    fun makeApiCall(searchData: String) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val apiResponse = retrofitRepository.getData(searchData)
                userLiveData.postValue(apiResponse)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
