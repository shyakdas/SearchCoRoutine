package com.search.coroutine.activity

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.search.coroutine.AddressAdapter
import com.search.coroutine.R
import com.search.coroutine.base.BaseActivity
import com.search.coroutine.model.Address
import com.search.coroutine.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : BaseActivity() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var addressList: ArrayList<Address>
    private lateinit var addressAdapter: AddressAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        initData()
        initEditText()
        initLayoutManager()
        initAdapter()
        initAddressData()
        initSearch("")
    }

    private fun initEditText() {
        address_edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                initSearch(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    private fun initData() {
        addressList = ArrayList()
    }

    private fun initAdapter() {
        addressAdapter = AddressAdapter(addressList)
        recycler_view.adapter = addressAdapter
    }

    private fun initLayoutManager() {
        recycler_view.layoutManager = LinearLayoutManager(this)
    }

    override fun initViewModel() {
        homeViewModel =
            ViewModelProvider(this, defaultViewModelProviderFactory).get(HomeViewModel::class.java)
    }

    private fun initSearch(search: String) {
        homeViewModel.fetchAddress(search)
    }

    private fun initError() {

    }

    private fun initAddressData() {
        homeViewModel.mutableAddressData.observe(this, Observer {
            addressList.clear()
            addressList.addAll(it)
            addressAdapter.notifyDataSetChanged()
        })
    }
}