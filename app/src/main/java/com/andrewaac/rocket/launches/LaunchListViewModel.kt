package com.andrewaac.rocket.launches

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrewaac.rocket.RocketApplication
import com.andrewaac.rocket.api.RetrofitFactory
import com.andrewaac.rocket.api.SpaceXService
import com.andrewaac.rocket.db.launch.Launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
    private var launchList = RocketApplication.db?.launchDao()?.getAllLaunches()
    private var filteredList = MutableLiveData<List<Launch>>()

    fun getLaunchList(): LiveData<List<Launch>>? {
        return launchList
    }

    fun getFilteredList(): LiveData<List<Launch>> {
        return filteredList
    }

    fun getLaunches() {
        Log.d(TAG, "Getting launches")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                delay(2000)
                spaceXService.getLaunches().enqueue(object : Callback<List<Launch>> {
                    override fun onFailure(call: Call<List<Launch>>, t: Throwable) {
                        Log.e(TAG, "Something went wrong getting the launches: ${t.message}")
                    }

                    override fun onResponse(
                        call: Call<List<Launch>>,
                        response: Response<List<Launch>>
                    ) {
                        val launches = response.body() as List<Launch>
                        RocketApplication.db?.launchDao()?.addLaunch(launches)
                    }

                })
            }
        }
    }

    fun filterListByCompleted() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                filteredList.postValue(RocketApplication.db?.launchDao()?.getSuccessfulLaunches())
            }
        }
    }

    fun orderListByCompleted() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                launchList?.value?.let {
                    val arrayList = ArrayList(it)
                    arrayList.sortBy { launch -> launch.launch_date_utc }
                    filteredList.postValue(arrayList)
                }
            }
        }
    }
}