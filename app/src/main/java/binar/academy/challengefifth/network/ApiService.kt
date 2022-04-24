package binar.academy.challengefifth.network

import binar.academy.challengefifth.modal.GetAllMovieResponse
import binar.academy.challengefifth.modal.Result
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("3/movie/popular?api_key=de13525edccd9e5a0725ca11cf179a09&language=en-US&page=1")
    fun allMovie(): Call<GetAllMovieResponse>
//    @POST("admin/auth/register")
//    fun registerAdmin(@Body registerRequest: RegisterRequest): Call<RegisterResponseItem>
}