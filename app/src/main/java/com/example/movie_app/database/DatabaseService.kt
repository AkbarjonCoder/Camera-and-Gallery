package com.example.movie_app.database

import com.example.movie_app.model.Contact

interface DatabaseService {
    fun addContact(contact: Contact)
    fun getAllContacts(): List<Contact>
    fun deleteContact(movie: Contact)
    fun updateContact(movie: Contact)
    fun getContactById(id: Int): Contact
}