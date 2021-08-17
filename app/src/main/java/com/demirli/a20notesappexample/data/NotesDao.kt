package com.demirli.a20notesappexample.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.demirli.a20notesappexample.model.Notes

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes")
    fun getAllNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNotes(notes: Notes)

    @Delete
    fun deleteNotes(notes: Notes)

    @Query("DELETE FROM notes")
    fun deleteAll()
}