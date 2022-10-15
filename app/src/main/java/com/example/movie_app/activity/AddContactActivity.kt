package com.example.movie_app.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movie_app.R
import com.example.movie_app.database.MyDatabase
import com.example.movie_app.databinding.ActivityAddMovieBinding
import com.example.movie_app.model.Contact
import com.example.movie_app.util.snack


class AddContactActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddMovieBinding.inflate(layoutInflater) }
    private lateinit var myDatabase: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.add_movie)
        myDatabase = MyDatabase(this)

        binding.btnAddContact.setOnClickListener {
            val name = binding.editName.text.toString().trim()
            val number = binding.editNumber.text.toString().trim()
            if (name.isNotBlank() && number.isNotBlank()) {
                myDatabase.addContact(Contact(name, number))
                snack(it, "Successfully saved!")
                startActivity(Intent(this, ContactListActivity::class.java))
            } else {
                snack(it, "Enter data!")
            }
        }


    }
}