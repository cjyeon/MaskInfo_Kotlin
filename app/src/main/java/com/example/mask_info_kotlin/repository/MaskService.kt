package com.example.mask_info_kotlin.repository

import com.example.mask_info_kotlin.model.StoreInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MaskService {
    @GET("sample.json?m=5000")
    suspend fun fetchStoreInfo(         //suspend -> 오랫동안 비동기로 수행될 수 있는 코드라는 의미로 사용
        @Query("lat") lat: Double,
        @Query("lng") lng: Double
    ): StoreInfo     //리턴 타입

    companion object {
        const val BASE_URL =
            "https://gist.githubusercontent.com/junsuk5/bb7485d5f70974deee920b8f0cd1e2f0/raw/063f64d9b343120c2cb01a6555cf9b38761b1d94/"
    }
}