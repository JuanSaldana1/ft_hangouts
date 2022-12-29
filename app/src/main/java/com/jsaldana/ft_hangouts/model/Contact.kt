package com.jsaldana.ft_hangouts.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "contacts")
data class Contact(
	@ColumnInfo(name = "id")
	@PrimaryKey(autoGenerate = true)
	var id: Int = 1,
	val name: String,
	val surname: String,
	val profileImage: String,
	val birthDate: String,
	val email: String
) : Serializable
