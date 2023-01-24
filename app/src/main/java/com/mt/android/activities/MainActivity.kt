package com.mt.android.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
 import com.mt.android.adapters.HomeImageAdapter
import com.mt.android.data.model.MainListDataResponse
 import com.mt.android.foxit.R
import com.mt.android.foxit.databinding.ActivityMainBinding
import com.mt.android.ui.classes.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ComponentActivity() {

    private lateinit var viewModelMainModel: HomeViewModel
    private lateinit var mainAdapter: HomeImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModelMainModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding.viewModelMain = viewModelMainModel


        txtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (txtSearch.text.toString().isEmpty()) {
                    recyclerView?.visibility = View.GONE
                    noData?.visibility = View.VISIBLE
                } else {
                    try {
                        getDataFromAPI(p0.toString())
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

    }


    /**
     * API Calling to get Emp Data in JSON Format
     */
    private fun getDataFromAPI(searchData: String) {
        viewModelMainModel.getUserDataList().observe(this) {
            if (it.data.isNotEmpty()) {
                setupUI(it.data)
                noData?.visibility = View.GONE
                recyclerView?.visibility = View.VISIBLE
            } else if (it.data.isEmpty()) {
                noData?.visibility = View.VISIBLE
                recyclerView?.visibility = View.GONE
                progressBar?.visibility = View.GONE
            }
        }
        viewModelMainModel.makeApiCall(searchData)
    }

    private fun setupUI(empList: ArrayList<MainListDataResponse>) {
        mainAdapter = HomeImageAdapter(this, empList)
        recyclerView?.layoutManager = LinearLayoutManager(this@MainActivity)
        progressBar?.visibility = View.GONE
        recyclerView?.adapter = mainAdapter
    }


}
