package binar.academy.challengefifth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import binar.academy.challengefifth.modal.GetAllMovieResponse
import binar.academy.challengefifth.modal.Result
import binar.academy.challengefifth.network.ApiClient
import binar.academy.challengefifth.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerViewModel : ViewModel() {
    private val _movie = MutableLiveData<List<Result>>()
    val movie: LiveData<List<Result>>
        get() = _movie

    init {
        loadMovie()
    }

    private fun loadMovie(){
//        ApiClient.retrofitService.allMovie()
//            .enqueue(object : retrofit2.Callback<List<Result>>{
//                override fun onResponse(
//                    call: Call<List<Result>>,
//                    response: Response<List<Result>>
//                ) {
//                   _movie.value = response.body()
//                }
//
//                override fun onFailure(call: Call<List<Result>>, t: Throwable) {
//                }
//
//            })
        ApiClient.retrofitService.allMovie()
            .enqueue(object : Callback<GetAllMovieResponse>{
                override fun onResponse(
                    call: Call<GetAllMovieResponse>,
                    response: Response<GetAllMovieResponse>
                ) {
                    _movie.value=response.body()?.results
                }

                override fun onFailure(call: Call<GetAllMovieResponse>, t: Throwable) {

                }

            })


    }

}