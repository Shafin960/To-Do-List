package com.example.help

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), NoteRVAdapter.INotesRVAdapter {
    lateinit var viewModel :NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView= findViewById<RecyclerView>(R.id.rview)
        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter=NoteRVAdapter(this,this)
        recyclerView.adapter=adapter

        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer { list ->
            list?.let{
                adapter.updateList(it)
            }
        })
    }
    override fun onItemclicked(note: Note) {
        viewModel.deleteNode(note)
        Toast.makeText(this,"Deleted",Toast.LENGTH_LONG).show()
    }
    fun submitData(view: View){
        val noteText=findViewById<EditText>(R.id.input).text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this,"Inserted",Toast.LENGTH_LONG).show()
        }
    }
}