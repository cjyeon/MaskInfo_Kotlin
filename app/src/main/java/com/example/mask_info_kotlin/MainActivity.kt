package com.example.mask_info_kotlin

import StoreAdapter
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val storeadapter = StoreAdapter()

        rv_store.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = storeadapter
        }

        //UI 업데이트
        viewModel.apply {
            itemLiveData.observe(this@MainActivity, Observer {
                storeadapter.updateItems(it)
                supportActionBar!!.setTitle("마스크 재고 있는 곳 : " + it.size.toString() + "곳")
            })
            loadingLiveData.observe(this@MainActivity, Observer { isLoading ->
                progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            })
        }
    }
}
