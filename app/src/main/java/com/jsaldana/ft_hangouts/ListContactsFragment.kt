package com.jsaldana.ft_hangouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.jsaldana.ft_hangouts.databinding.FragmentListContactsBinding
import com.jsaldana.ft_hangouts.model.Contact

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListContactsFragment : Fragment() {

	private var _binding: FragmentListContactsBinding? = null


	private lateinit var database: AppDatabase
	private lateinit var contact: Contact
	private lateinit var contactLiveData: LiveData<Contact>
	private val EDIT = 2

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentListContactsBinding.inflate(inflater, container, false)
		loadAllContactsFromDatabase()
		binding.createContactButton.setOnClickListener {
			findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
		}
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

	}

	private fun loadAllContactsFromDatabase() {
		var contactList = emptyList<Contact>()
		val database = AppDatabase.getDatabase(requireContext())
		database.contact().getAllContacts().observe(viewLifecycleOwner) {
			contactList = it
			val adapter = ContactAdapter(requireContext(), contactList)
			binding.contactsListView.adapter = adapter
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}