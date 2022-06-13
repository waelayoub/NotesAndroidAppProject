package com.example.homework2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework2.NotesApp.Model.Notes
import com.example.homework2.NotesApp.Utils.NotesAdapter
import com.example.homework2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityMainBinding.inflate(
            layoutInflater
        )
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnAddNotes.setOnClickListener {
            finish()
            val intent=Intent(this, NotesWriting::class.java)
            startActivity(intent)
        }
        xnkjsbkjs


        // List of Items
        val todoListSingleItem = mutableListOf<Notes>()
        val sharedPreferences: SharedPreferences = getSharedPreferences("TheNotesFile", MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        val secNumber=sharedPreferences.getInt("IndexOfItems", 0)


        // To get the info from memory and use it as objects
        if(secNumber!=0) {
            var i:Int=0
            while(i<secNumber){
                val mytitle=sharedPreferences.getString("Title${i}", "")
                val myNote=sharedPreferences.getString("Note${i}", "")
                val myDate=sharedPreferences.getString("Date${i}","")
                todoListSingleItem.add(Notes(mytitle!!, myNote!!, myDate!!))
                i++
            }
        }


        val adapter = NotesAdapter(todoListSingleItem,sharedPreferences)
        binding.rvNotes.adapter = adapter
        binding.rvNotes.layoutManager = LinearLayoutManager(this)


    }
}