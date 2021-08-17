package com.demirli.a20notesappexample.ui.activity

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.demirli.a20notesappexample.data.NotesDao
import com.demirli.a20notesappexample.data.NotesDb
import com.demirli.a20notesappexample.model.Notes

class MainRepository(context: Context) {
    private val db by lazy{NotesDb.getInstance(context)}
    private val dao by lazy{db.notesDao()}

    fun getAllNotes(): LiveData<List<Notes>> = dao.getAllNotes()
    fun addNotes(notes: Notes) {
        AddNoteAsyncTask(dao).execute(notes)
    }
    fun deleteNotes(notes: Notes){
        DeleteNoteAsyncTask(dao).execute(notes)
    }

    private class AddNoteAsyncTask(val dao: NotesDao): AsyncTask<Notes,Void,Void>(){
        override fun doInBackground(vararg params: Notes?): Void? {
            dao.addNotes(params[0]!!)
            return null
        }
    }

    private class DeleteNoteAsyncTask(val dao: NotesDao): AsyncTask<Notes,Void,Void>(){
        override fun doInBackground(vararg params: Notes?): Void? {
            dao.deleteNotes(params[0]!!)
            return null
        }
    }


}