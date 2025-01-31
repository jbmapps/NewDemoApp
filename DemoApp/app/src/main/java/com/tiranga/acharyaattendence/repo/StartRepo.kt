package com.tiranga.acharyaattendence.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tiranga.acharyaattendence.api.RetroAPI
import com.tiranga.acharyaattendence.utils.*
import com.tiranga.acharyaattendence.model.NetworkResult
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.ResponseBody
import javax.inject.Inject

class StartRepo @Inject constructor(
    @ApplicationContext val context: Context,
    private val retroAPI: RetroAPI
) {

    private val _updateData = MutableLiveData<NetworkResult<ResponseBody>>()
    val updateData: LiveData<NetworkResult<ResponseBody>>
        get() = _updateData

    suspend fun fetchUpdateData(requestBody: String) {
        try {
            context.handleResponse(retroAPI.updateApi(requestBody), _updateData)
        } catch (e: Exception) {
            e.printStackTrace()
            _updateData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }

}