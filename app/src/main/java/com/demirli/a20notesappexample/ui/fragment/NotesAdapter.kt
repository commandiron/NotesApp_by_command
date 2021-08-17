package com.demirli.a20notesappexample.ui.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demirli.a20notesappexample.R
import com.demirli.a20notesappexample.model.Notes

class NotesAdapter(var context: Context, var notesList: List<Notes>, var onNoteItemClickListener: OnNoteItemClickListener ):
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = notesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTitle.text = notesList[position].title

        holder.itemView.setOnClickListener {
            onNoteItemClickListener.onNoteItemClick(notesList[position])
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val noteTitle = view.findViewById<TextView>(R.id.note_title_tv)
    }

    interface OnNoteItemClickListener {
        fun onNoteItemClick(notes: Notes)
    }
}