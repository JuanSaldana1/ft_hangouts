package com.jsaldana.ft_hangouts

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jsaldana.ft_hangouts.databinding.FragmentCreateContactBinding

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

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}