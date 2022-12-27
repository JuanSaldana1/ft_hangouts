package com.jsaldana.ft_hangouts.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "contacts")
data class Contact(
	@ColumnInfo(name = "id")
	@PrimaryKey(autoGenerate = true)
	private var id: Int,
	val name: String,
	val surname: String,
	val birthDate: String,
	val email: String
) : Serializable
