package com.demirli.a20notesappexample.data

import android.content.Context
import android.os.AsyncTask
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.demirli.a20notesappexample.model.Notes
import java.time.chrono.IsoChronology

@Database(entities = arrayOf(Notes::class),version = 1)
abstract class NotesDb: RoomDatabase(){
    abstract fun notesDao(): NotesDao

    companion object{

        var INSTANCE: NotesDb? = null

        fun getInstance(context: Context): NotesDb{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDb::class.java,
                    "notes_db")
                    .addCallback(roomDbCallback)
                    .build()

            }
            return INSTANCE as NotesDb
        }

        private val roomDbCallback = object: RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateAsyncTask(INSTANCE).execute()
            }
        }

        class PopulateAsyncTask(private val db: NotesDb?): AsyncTask<Void, Void, Void>() {
            private val dao: NotesDao? by lazy { db?.notesDao() }
            override fun doInBackground(vararg params: Void?): Void? {

                var notes = Notes(
                    title = "Test 1",
                    content = "Test, test, test.")
                dao?.addNotes(notes)

                return null
            }
        }
    }
}