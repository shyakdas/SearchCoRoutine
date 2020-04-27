package com.search.coroutine.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.search.coroutine.model.Address
import kotlinx.android.synthetic.main.item_address.view.*

class AddressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(address: Address) {
        itemView.address_text.text = address.addressString
    }
}