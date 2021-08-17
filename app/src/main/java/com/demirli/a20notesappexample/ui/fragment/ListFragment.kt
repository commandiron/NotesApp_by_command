package com.demirli.a20notesappexample.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.demirli.a20notesappexample.R
import com.demirli.a20notesappexample.model.Notes
import com.demirli.a20notesappexample.ui.activity.MainViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(), NotesAdapter.OnNoteItemClickListener {

    private lateinit var adapter: NotesAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(MainViewModel::class.java)

        viewModel.getAllNotes.observe(this, Observer {
            notes_recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = NotesAdapter(activity!!.applicationContext,it,this)
            notes_recyclerView.adapter = adapter
        })

        list_fragment_add_note_fab.setOnClickListener {
            replaceToNoteFragment(Notes(title = "", content = ""))
        }
    }
    override fun onNoteItemClick(notes: Notes) {
        replaceToNoteFragment(notes)
    }

    private fun replaceToNoteFragment(notes: Notes){
        fragmentManager!!.beginTransaction().replace(
            R.id.container,
            NoteFragment.newInstance(notes)
        ).commit()
    }
}
