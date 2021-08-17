package com.demirli.a20notesappexample.ui.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.demirli.a20notesappexample.model.Notes

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository: MainRepository by lazy { MainRepository(application)}

    val getAllNotes: LiveData<List<Notes>> by lazy { repository.getAllNotes() }

    fun addNotes(notes: Notes) = repository.addNotes(notes)

    fun deleteNotes(notes: Notes) = repository.deleteNotes(notes)


}