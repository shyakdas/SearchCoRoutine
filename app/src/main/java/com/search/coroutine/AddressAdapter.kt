package com.search.coroutine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.search.coroutine.model.Address
import com.search.coroutine.viewholder.AddressViewHolder

class AddressAdapter(var list: ArrayList<Address>) : RecyclerView.Adapter<AddressViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        return AddressViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_address,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bind(list[position])
    }
}