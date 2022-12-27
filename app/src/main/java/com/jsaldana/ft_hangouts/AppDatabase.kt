package com.jsaldana.ft_hangouts

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jsaldana.ft_hangouts.interfaces.ContactDAO
import com.jsaldana.ft_hangouts.model.Contact
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Contact::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

	abstract fun contact(): ContactDAO

	companion object {
		@Volatile
		private var INSTANCE: AppDatabase? = null

		@OptIn(InternalCoroutinesApi::class)
		fun getDatabase(context: Context): AppDatabase {
			val tempInstance = INSTANCE
			if (tempInstance != null)
				return tempInstance
			synchronized(this) {
				val instance = Room.databaseBuilder(
					context.applicationContext,
					AppDatabase::class.java,
					"ft_hangouts"
				).build()
				INSTANCE = instance
				return instance
			}
		}
	}
}