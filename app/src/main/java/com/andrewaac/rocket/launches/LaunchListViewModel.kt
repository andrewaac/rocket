package com.andrewaac.rocket.launches

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrewaac.rocket.api.RetrofitFactory
import com.andrewaac.rocket.api.SpaceXService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LaunchListViewModel : ViewModel() {

    companion object {
        const val TAG = "LaunchListViewModel"
    }

    private var spaceXService = RetrofitFactory.instance.create(SpaceXService::class.java)

    init {
        getLaunches()
    }

    private fun getLaunches() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                spaceXService.getLaunches().enqueue(object : Callback<List<Launch>> {
                    override fun onFailure(call: Call<List<Launch>>, t: Throwable) {
                        Log.e(TAG, "Something went wrong getting the launches: ${t.message}")
                    }

                    override fun onResponse(
                        call: Call<List<Launch>>,
                        response: Response<List<Launch>>
                    ) {
                        val launches = response.body() as List<Launch>
                        for (launch in launches) {
                            Log.d(TAG, "$launch")
                        }
                    }

                })
            }
        }
    }
}