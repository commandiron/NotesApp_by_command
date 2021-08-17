package com.demirli.a20notesappexample.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.demirli.a20notesappexample.R
import com.demirli.a20notesappexample.model.Notes
import com.demirli.a20notesappexample.ui.activity.MainViewModel
import com.demirli.a20notesappexample.ui.fragment.ListFragment
import kotlinx.android.synthetic.main.fragment_note.*


class NoteFragment : Fragment() {

    companion object {
        fun newInstance(notes: Notes): NoteFragment{
            val args = Bundle()
            args.putParcelable("noteFragment_pass_key",notes)

            val fragment = NoteFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var notesFromFragment: Notes? = null

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesFromFragment = arguments?.get("noteFragment_pass_key") as Notes

        note_fragment_title_et.setText( notesFromFragment?.title)
        note_fragment_content_et.setText( notesFromFragment?.content)

        viewModel = ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(MainViewModel::class.java)

        note_fragment_save_btn.setOnClickListener {
            if (note_fragment_title_et.text.toString() == ""){
                Toast.makeText(activity!!.applicationContext,"Title area is empty.", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.addNotes(Notes(notesFromFragment!!.nID,note_fragment_title_et.text.toString(),note_fragment_content_et.text.toString()))
                replaceToListFragment()
            }
        }

        note_fragment_delete_btn.setOnClickListener {
            viewModel.deleteNotes(notesFromFragment!!)
            replaceToListFragment()
        }
    }

    private fun replaceToListFragment() {
        fragmentManager!!.beginTransaction().replace(
            R.id.container,
            ListFragment()
        ).commit()
    }
}
