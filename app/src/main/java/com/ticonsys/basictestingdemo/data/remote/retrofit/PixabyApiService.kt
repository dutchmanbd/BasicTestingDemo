package com.ticonsys.basictestingdemo.data.remote.retrofit

import com.ticonsys.basictestingdemo.BuildConfig
import com.ticonsys.basictestingdemo.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabyApiService {

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): Response<ImageResponse>

}