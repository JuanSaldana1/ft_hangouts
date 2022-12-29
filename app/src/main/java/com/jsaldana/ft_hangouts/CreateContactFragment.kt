package com.jsaldana.ft_hangouts

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jsaldana.ft_hangouts.databinding.FragmentCreateContactBinding
import com.jsaldana.ft_hangouts.model.Contact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CreateContactFragment : Fragment() {
	private val SELECTCODE = 1
	private var pictureUri: Uri? = null

	private var _binding: FragmentCreateContactBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {

		_binding = FragmentCreateContactBinding.inflate(inflater, container, false)
		selectProfileImage()
		binding.saveNewContactButton.setOnClickListener {
			saveNewContact()
		}
		return binding.root
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		when {
			requestCode == SELECTCODE && resultCode == Activity.RESULT_OK -> {
				pictureUri = data!!.data
				binding.contactPictureImageView.setImageURI(pictureUri)
			}
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}

	private fun selectProfileImage() {
		binding.contactPictureImageView.setOnClickListener {
			ImageController.selectPicture(this, SELECTCODE)
		}
	}

	private fun saveNewContact() {
		val database = AppDatabase.getDatabase(requireContext())

		val name = binding.contactNameEditText.text.toString()
		val surname = binding.contactSurnameEditText.text.toString()
		val birthDate = binding.contactBirthDateEditText.text.toString()
		val email = binding.contactEmailEditText.text.toString()

		val contact =
			Contact(0, name, surname, R.drawable.ic_round_image_24.toString(), birthDate, email)

		CoroutineScope(Dispatchers.IO).launch {
			val id = database.contact().insertContact(contact)[0]
			pictureUri?.let {
				ImageController.savePicture(requireContext(), id, it)
			}
		}
		findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}