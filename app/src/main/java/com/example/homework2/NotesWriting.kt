package com.example.homework2

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.homework2.databinding.ActivityNotesWritingBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class NotesWriting : AppCompatActivity() {
    private lateinit var binding: ActivityNotesWritingBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityNotesWritingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Getting my current date and time
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formatted = current.format(formatter)

        // To create the shared Pref. file
        val sharedPreferences=getSharedPreferences("TheNotesFile", MODE_PRIVATE)
        val editor=sharedPreferences.edit()

        binding.tvDate.text=formatted
        binding.btnSaveNotes.setOnClickListener {
            finish()

            val secNumber=sharedPreferences.getInt("IndexOfItems", 0)
            // secNumber is for getting how many items are saved

            editor.apply{


                // to save my note To not get wiped + Im getting my new note passed to the first activity
                val StrTitleKey:String="Title${secNumber}"
                val StrNoteKey:String="Note${secNumber}"
                val StrDateKey:String="Date${secNumber}"

                putString(StrTitleKey, binding.etTitle.text.toString())
                putString(StrNoteKey, binding.etNote.text.toString())
                putString(StrDateKey, binding.tvDate.text.toString())


                putInt("IndexOfItems",secNumber+1).apply()
            }

            println(sharedPreferences.getInt("IndexOfItems", 0))

            binding.etTitle.text?.clear()
            binding.etNote.text?.clear()
            val myIntent=Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }
    }
}