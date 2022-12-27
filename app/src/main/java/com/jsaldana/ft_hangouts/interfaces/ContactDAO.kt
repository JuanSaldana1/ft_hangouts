package com.jsaldana.ft_hangouts.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jsaldana.ft_hangouts.model.Contact

@Dao
interface ContactDAO {
	@Query("SELECT * FROM contacts")
	fun getAllContacts(): LiveData<List<Contact>>

	@Query("Select * FROM contacts WHERE id = :id")
	fun getContactById(id: Int): LiveData<Contact>

	@Insert
	fun insertContact(vararg contact: Contact): List<Long>

	@Update
	fun editContact(contact: Contact)

	@Delete
	fun deleteContact(contact: Contact)
}