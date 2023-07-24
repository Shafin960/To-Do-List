package com.example.help

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao :NoteDao) {
    val getAllNodes : LiveData<List<Note>> =noteDao.getAllNotes()

    suspend fun insert (note : Note){
        noteDao.insert(note)
    }
    suspend fun delete(note: Note){
        noteDao.delete(note)
    }
}