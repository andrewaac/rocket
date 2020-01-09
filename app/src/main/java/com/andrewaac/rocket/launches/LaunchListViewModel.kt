package com.andrewaac.rocket.launches

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrewaac.rocket.RocketApplication
import com.andrewaac.rocket.api.RetrofitFactory
import com.andrewaac.rocket.api.SpaceXService
import com.andrewaac.rocket.db.launch.Launch
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
    private var launchList = RocketApplication.db?.launchDao()?.getAllLaunches()


    init {
        getLaunches()
    }

    fun getLaunchList(): LiveData<List<Launch>>? {
        return launchList
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
                        RocketApplication.db?.launchDao()?.addLaunch(launches)
                        for (launch in launches) {
                            Log.d(TAG, "$launch")
                        }
                    }

                })
            }
        }
    }
}