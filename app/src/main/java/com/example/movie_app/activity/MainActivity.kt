package com.example.movie_app.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity
import com.example.movie_app.R
import com.example.movie_app.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.main_activity)


        binding.btnAddContact.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }
        binding.btnContactList.setOnClickListener {
            startActivity(Intent(this, ContactListActivity::class.java))
        }
    }
}