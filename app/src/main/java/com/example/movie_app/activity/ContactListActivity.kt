package com.example.movie_app.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_app.R
import com.example.movie_app.adapter.ContactAdapter
import com.example.movie_app.database.MyDatabase
import com.example.movie_app.databinding.ActivityMovieListBinding
import com.example.movie_app.model.Contact
import com.example.movie_app.util.snack

class ContactListActivity : AppCompatActivity() {

    private lateinit var myDatabase: MyDatabase
    private lateinit var contactAdapter: ContactAdapter
    private val binding by lazy { ActivityMovieListBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.contact_list)
        myDatabase = MyDatabase(this)
        contactAdapter = ContactAdapter()
        contactAdapter.contactList = myDatabase.getAllContacts() as MutableList<Contact>

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ContactListActivity)
            adapter = contactAdapter
        }
        contactAdapter.onDeleteClick = { contact, pos ->
            AlertDialog.Builder(this).apply {
                setTitle("Delete")
                setMessage("Do you want to delete this contact?")
                setNegativeButton("No", null)
                setPositiveButton("Yes") { _, _ ->
                    snack(binding.root, "Successfully deleted!")
                    contactAdapter.notifyItemRemoved(pos)
                    contactAdapter.contactList.removeAt(pos)
                    myDatabase.deleteContact(contact)
                }
            }.create().show()
        }

    }
}