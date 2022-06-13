package com.example.homework2.NotesApp.Utils
import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.homework2.NotesApp.Model.Notes
import com.example.homework2.R
import com.example.homework2.databinding.ActivityMainBinding
import com.example.homework2.databinding.NotesItemsLayout1Binding


class NotesAdapter(var NotesList:MutableList<Notes>, var sharedPreferences: SharedPreferences) :RecyclerView.Adapter<NotesAdapter.NotesViewHolder>(){
    inner class NotesViewHolder(val binding: NotesItemsLayout1Binding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(NotesItemsLayout1Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        var theNote:String=""
        theNote+=NotesList[position].title
        theNote+="\n"
        theNote+=NotesList[position].Note
        holder.binding.tvTheNote.text=theNote
        holder.binding.tvNotesDate.text=NotesList[position].currentDate
        holder.binding.btnDeleteNote.setOnClickListener{
            var j:Int=position
            val editor=sharedPreferences.edit()
            val secNumber=sharedPreferences.getInt("IndexOfItems", 0)


            while (j<secNumber-1){
                val mytitle=sharedPreferences.getString("Title${j+1}", "")
                val myNote=sharedPreferences.getString("Note${j+1}", "")
                val myDate=sharedPreferences.getString("Date${j+1}","")

                println("Title${j+1} is ${mytitle}")

                editor.apply{

                    putString("Title${j}", mytitle)
                    putString("Note${j}", myNote)
                    putString("Date${j}", myDate)
                }
                println("Substitution")
                println("Title${j} is ${mytitle}")

                j++
            }
            sharedPreferences.edit().remove("Title${secNumber-1}").apply()
            sharedPreferences.edit().remove("Note${secNumber-1}").apply()
            sharedPreferences.edit().remove("Date${secNumber-1}").apply()

            val mytitle=sharedPreferences.getString("Title${secNumber}", "deleted")
            println("value of deleted is : ${mytitle}")

            editor.apply {putInt("IndexOfItems",secNumber-1).apply()}
            NotesList.removeAt(position)
            println("Now Printing the NotesList")
            println(NotesList)
            this.notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return NotesList.size
    }


}