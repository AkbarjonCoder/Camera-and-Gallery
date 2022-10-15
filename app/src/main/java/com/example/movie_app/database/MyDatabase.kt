package com.example.movie_app.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.movie_app.adapter.ContactAdapter
import com.example.movie_app.model.Contact
import com.example.movie_app.util.Constants

class MyDatabase(context: Context) :
    SQLiteOpenHelper(context, Constants.DB_NAME, null, Constants.DB_VERSION), DatabaseService {
    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "CREATE TABLE ${Constants.TABLE_NAME} (${Constants.ID} INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, ${Constants.NAME} TEXT NOT NULL, ${Constants.NUMBER} TEXT NOT NULL)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${Constants.TABLE_NAME}")
        onCreate(db)
    }

    override fun addContact(contact: Contact) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constants.NAME, contact.name)
        contentValues.put(Constants.NUMBER, contact.number)
        database.insert(Constants.TABLE_NAME, null, contentValues)
        database.close()
    }

    override fun getAllContacts(): List<Contact> {
        val database = this.readableDatabase
        val query = "SELECT * FROM ${Constants.TABLE_NAME}"
        val movieList = ArrayList<Contact>()
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val movie = Contact(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                movieList.add(movie)
            } while (cursor.moveToNext())
        }
        return movieList
    }

    override fun deleteContact(contact: Contact) {
        val database = this.writableDatabase
        database.delete(Constants.TABLE_NAME, "${Constants.ID} = ?", arrayOf(contact.toString()))
        database.close()
    }

    override fun updateContact(movie: Contact) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constants.ID, movie.id)
        contentValues.put(Constants.NAME, movie.name)
        contentValues.put(Constants.NUMBER, movie.number)
        database.update(
            Constants.TABLE_NAME,
            contentValues,
            "${Constants.ID} = ?",
            arrayOf(movie.id.toString())
        )
    }

    override fun getContactById(id: Int): Contact {
        val database = this.readableDatabase
        val cursor = database.query(
            Constants.TABLE_NAME,
            arrayOf(Constants.ID, Constants.NAME, Constants.NUMBER),
            "${Constants.ID} = ?",
            arrayOf("$id"),
            null,
            null,
            null
        )
        cursor?.moveToFirst()
        return Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2))
    }


}