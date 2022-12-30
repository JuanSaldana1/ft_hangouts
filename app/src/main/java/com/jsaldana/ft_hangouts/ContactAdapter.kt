package com.jsaldana.ft_hangouts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.jsaldana.ft_hangouts.R
import com.jsaldana.ft_hangouts.model.Contact

class ContactAdapter(
	private val ctxt: Context,
	private val contactList: List<Contact>
) : ArrayAdapter<Contact>(ctxt, 0, contactList) {
	override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
		val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
		val currentContact = contactList[position]
		return layout
	}
}