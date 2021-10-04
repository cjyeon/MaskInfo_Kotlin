package com.example.mask_info_kotlin

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mask_info_kotlin.model.Store
import com.example.mask_info_kotlin.repository.MaskService
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.stream.Collectors

class MainViewModel : ViewModel() {
    val itemLiveData = MutableLiveData<List<Store>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    //java의 MaskService maskservice; 와 같은 뜻(나중에 초기화하겠다는 뜻)
//    var service: MaskService
    private var service: MaskService
    private lateinit var locationProvider: FusedLocationProviderClient

    //init에서 초기 보장하기 때문에 위에서 lateinit 안써도 됨
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(MaskService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        service = retrofit.create(MaskService::class.java)

        fetchStoreInfo()
    }

    @SuppressLint("MissingPermission")
    fun fetchStoreInfo() {
        //로딩 시작
        loadingLiveData.value = true

//        locationProvider.lastLocation
//            .addOnSuccessListener { location ->
//                location?.let {
                    viewModelScope.launch {
                        val storeInfo =
//                            service.fetchStoreInfo(location.latitude, location.longitude)
                            service.fetchStoreInfo(37.266389, 126.999333)
                        itemLiveData.value = storeInfo.stores.filter { store ->
                            store.remain_stat != null
                        }

                        //로딩 끝
                        loadingLiveData.value = false
                    }
//                }
//            }
    }
}