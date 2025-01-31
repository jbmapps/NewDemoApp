package com.tiranga.acharyaattendence.ui.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiranga.acharyaattendence.model.NetworkResult
import com.tiranga.acharyaattendence.repo.StartRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(private val startRepo: StartRepo) : ViewModel() {

    val updateData: LiveData<NetworkResult<ResponseBody>>
        get() = startRepo.updateData

    fun fetchUpdateData(requestBody: String) {
        viewModelScope.launch(Dispatchers.IO) {
            startRepo.fetchUpdateData(requestBody)
        }
    }
}