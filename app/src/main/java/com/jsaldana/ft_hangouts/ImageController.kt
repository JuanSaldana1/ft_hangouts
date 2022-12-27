package com.jsaldana.ft_hangouts

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import java.io.File

object ImageController {
	fun selectPicture(fragment: Fragment, code: Int) {
		val intent = Intent(Intent.ACTION_PICK)
		intent.type = "image/*"
		fragment.startActivity(intent)
	}

	fun savePicture(context: Context, id: Long, uri: Uri) {
		val file = File(context.filesDir, id.toString())
		val bytes = context.contentResolver.openInputStream(uri)?.readBytes()!!
		file.writeBytes(bytes)
	}

	fun obtainPictureUri(context: Context, id: Long): Uri {
		val file = File(context.filesDir, id.toString())
		return if (file.exists())
			Uri.fromFile(file)
		else
			Uri.parse(R.drawable.ic_round_account_circle_24.toString())
	}

	fun deletePicture(context: Context, id: Long) {
		val file = File(context.filesDir, id.toString())
		file.delete()
	}
}